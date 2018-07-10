package jcrm.pp.ua.crmsystem.customClasses;

import jcrm.pp.ua.crmsystem.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {

//        List<User> users = new ArrayList<>();
//        User user = new User();
//           user.setFirstName("John");
//           user.setLastName("Doe");
//           user.setUsername("user");
//           user.setPassword("password");
//           user.setRoles(new String[] {"ROLE_USER"});
//        users.add(user);
//
//        User admin = new User();
//           admin.setFirstName("Richard");
//           admin.setLastName("Roe");
//           admin.setUsername("admin");
//           admin.setPassword("password");
//           admin.setRoles(new String[] {"ROLE_USER", "ROLE_ADMIN"});
//        users.add(admin);
//
//        userRepository.save(users);
    }
}
