package jcrm.pp.ua.crmsystem.customClasses.registration;

import jcrm.pp.ua.crmsystem.entities.Imp.User;
import jcrm.pp.ua.crmsystem.entities.Privilege;
import jcrm.pp.ua.crmsystem.entities.Role;
import jcrm.pp.ua.crmsystem.repositories.PrivilegeRepo;
import jcrm.pp.ua.crmsystem.repositories.RoleRepo;
import jcrm.pp.ua.crmsystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//Make @Bean! and setup in configs admin name and password

    boolean alreadySetup = false;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PrivilegeRepo privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;

            /*
        * ROLE_SYSTEM(CREATE_ACCOUNT_PRIVILEGE)
        * ROLE_GUEST(READ_PRIVILEGE)
        * ROLE_USER(
        *       WRITE_PRIVILEGE,
        *       DELETE_PRIVILEGE)
        * ROLE_MANAGER(
        *       CREATE_GUEST_PRIVILEGE,
        *       CREATE_USER_PRIVILEGE,
        *       DELETE_GUEST_PRIVILEGE,
        *       DELETE_USER_PRIVILEGE,
        *       BACK_UP_PRIVILEGE)
        * ROLE_AUDITOR(
        *       PHYSICAL_BACK_UP_PRIVILEGE)
        * ROLE_ADMINISTRATOR(
        *       CREATE_MANAGER_PRIVILEGE,
        *       CREATE_AUDITOR_PRIVILEGE,
        *       DELETE_MANAGER_PRIVILEGE,
        *       DELETE_AUDITOR_PRIVILEGE)
        * */

        Map<String, Privilege> privilegesMap = new HashMap<>();

        for (Privileges p : Privileges.values()) {
            Privilege createdPrivilege = createPrivilegeIfNotFound(p.toString());
            privilegesMap.put(p.toString(), createdPrivilege);
        }

        for (Roles r : Roles.values()) {
            List<Privilege> privileges = new ArrayList<>();
            for (Privileges p : r.privileges()) {
                privileges.add(privilegesMap.get(p.toString()));
            }
            createRoleIfNotFound(r.toString(), privileges);
        }
        entityManager.flush();

        createUserSystemIfNotFound();

        alreadySetup = true;
    }

    void createUserSystemIfNotFound() {

        User system = userRepository.findByUsername("system");
        if (system == null) {
            List<String> listQueries = new ArrayList<>();
            listQueries.add("INSERT INTO user (id, username, enabled) VALUES ((SELECT next_val FROM hibernate_sequence LIMIT 1) ,'system', TRUE);");
            listQueries.add("INSERT INTO contact (id) VALUES ((SELECT next_val FROM hibernate_sequence LIMIT 1));");
            listQueries.add("INSERT INTO base_client (id) VALUES ((SELECT next_val FROM hibernate_sequence LIMIT 1));");
            listQueries.add("INSERT INTO base_business_obj (id, b_o_type, isDeleted, physicalRemoval, optlock) VALUES ((SELECT next_val FROM hibernate_sequence LIMIT 1),'user', FALSE, FALSE, 0);");
            listQueries.add("INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT next_val FROM hibernate_sequence LIMIT 1),(SELECT role.id FROM role role WHERE role.name = 'ROLE_SYSTEM'));");
            listQueries.add("UPDATE hibernate_sequence SET next_val = next_val + 1;");

            for (String s : listQueries) {
                Query createUserSystem = entityManager.createNativeQuery(s);
                createUserSystem.executeUpdate();
            }
        }

        User sysAdmin = userRepository.findByUsername("sysAdmin");
        if (sysAdmin == null) {//
            sysAdmin = new User();
            sysAdmin.setUsername("sysAdmin");
            sysAdmin.setPassword("173247");
            userRepository.save(sysAdmin);
        }
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}