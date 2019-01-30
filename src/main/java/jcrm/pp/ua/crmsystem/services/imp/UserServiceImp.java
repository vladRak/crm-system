package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.entities.impl.User;
import jcrm.pp.ua.crmsystem.repositories.UserRepo;
import jcrm.pp.ua.crmsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean removeUser(User admin) {
        return false;
    }

    @Override
    public boolean blockUser(Long id) {
        return false;
    }

    @Override
    public boolean userStatus(Long id) {
        return false;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        userRepo.delete(id);

        return false;
    }
}
