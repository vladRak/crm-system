package jcrm.pp.ua.crmsystem.customClasses.registration;

import jcrm.pp.ua.crmsystem.configs.UserAwareUserDetails;
import jcrm.pp.ua.crmsystem.entities.User;
import jcrm.pp.ua.crmsystem.entities.Privilege;
import jcrm.pp.ua.crmsystem.entities.Role;
import jcrm.pp.ua.crmsystem.repositories.RoleRepo;
import jcrm.pp.ua.crmsystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    //@Autowired
    //private IUserService service;

    //@Autowired
    //private MessageSource messages;

    @Autowired
    private RoleRepo roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(email);
        return new UserAwareUserDetails(user, user.getRoles());


//        User user = userRepository.findByUsername(email);
//
//        if (user == null) {
//            user = new User();
//            user.setUsername(email);
//            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
//            return new UserAwareUserDetails(user, user.getRoles());
//        }

//                if (user == null) {
//            return new org.springframework.security.core.userdetails.User(
//                    " ", " ", true, true, true, true,
//                    getAuthorities(Arrays.asList(
//                            roleRepository.findByName("ROLE_USER"))));
//        }

//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(), user.getPassword(), user.isEnabled(), true, true,
//                true, getAuthorities(user.getRoles()));
//            return new UserAwareUserDetails(user, user.getRoles());

    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
