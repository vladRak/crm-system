package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.entities.User;
import jcrm.pp.ua.crmsystem.entities.Account;
import jcrm.pp.ua.crmsystem.entities.Company;
import jcrm.pp.ua.crmsystem.entities.Email;
import jcrm.pp.ua.crmsystem.repositories.AccountRepo;
import jcrm.pp.ua.crmsystem.repositories.RoleRepo;
import jcrm.pp.ua.crmsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AccountServiceImp implements AccountService{

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    RoleRepo roleRepository;

    @PreAuthorize("hasRole('ANONYMOUS')")
    public boolean createAccount(User user, boolean demo){
        try {
            Email email = new Email();
            email.setEmail(user.getUsername());

            user.addEmails(Arrays.asList(email));

            Company company = new Company();
            company.setResponsible(user);
            user.setCompany(company);
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMINISTRATOR")));

            Account account = new Account();
            account.addClients(Arrays.asList(user,company));

            if (demo) demoData(account);
            accountRepo.save(account);

            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
//    public boolean importClients(byte[] file) throws Exception {
//        ClamAvClient clamAvClient = new ClamAvClient();
//        return clamAvClient.scan(file);
//    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public boolean removeAccount(User admin){
        try {
            accountRepo.delete(admin.getAccount());
            return  true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public boolean blockAccount(Long id){
        try {
            Account account = accountRepo.findOne(id);
            account.setEnable(false);
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public boolean accountStatus(Long id){
        try {
            return accountRepo.findOne(id).isEnable();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public boolean deleteAccount(Long id){
        try {
            accountRepo.delete(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public Page<Account> getAllAccounts(Pageable pageable){
        try {
            return accountRepo.findAll(pageable);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public Account getAccountById(Long id){
        try {
           return accountRepo.findOne(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    protected void demoData(Account account){

    }
}
