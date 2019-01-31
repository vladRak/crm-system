package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.domain.entity.Account;
import jcrm.pp.ua.crmsystem.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    boolean createAccount(User user, boolean demo);
    boolean removeAccount(User admin);
    boolean blockAccount(Long id);
    boolean accountStatus(Long id);
    Page<Account> getAllAccounts(Pageable pageable);
    Account getAccountById(Long id);
    boolean deleteAccount(Long id);

}
