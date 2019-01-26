package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    boolean createUser(User user);
    boolean removeUser(User admin);
    boolean blockUser(Long id);
    boolean userStatus(Long id);
    Page<User> getAllUsers(Pageable pageable);
    User getUserById(Long id);
    boolean deleteUser(Long id);
}
