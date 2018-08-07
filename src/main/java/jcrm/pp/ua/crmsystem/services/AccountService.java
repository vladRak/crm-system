package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.entities.Imp.AccountImp;
import jcrm.pp.ua.crmsystem.entities.Imp.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    boolean createAccount(User user, boolean demo);
    boolean removeAccount(User admin);
    boolean blockAccount(Long id);
    boolean accountStatus(Long id);
    Page<AccountImp> getAllAccounts(Pageable pageable);
    AccountImp getAccountById(Long id);
    boolean deleteAccount(Long id);

}
