package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.customClasses.registration.ClamAvClient;
import jcrm.pp.ua.crmsystem.customClasses.registration.VirusResolver;
import jcrm.pp.ua.crmsystem.entities.Imp.*;
import jcrm.pp.ua.crmsystem.repositories.AccountRepo;
import jcrm.pp.ua.crmsystem.repositories.RoleRepo;
import jcrm.pp.ua.crmsystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

@Service
public class AccountServiceImp implements AccountService{

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    RoleRepo roleRepository;

    VirusResolver virusResolver = VirusResolver.getInstance();

    @PreAuthorize("hasRole('ANONYMOUS')")
    public void addAccount(User user, boolean demo){

        Email email = new Email();
        email.setEmail(user.getUsername());

        user.addEmails(Arrays.asList(email));

        Company company = new Company();
        company.setResponsible(user);
        user.setCompany(company);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMINISTRATOR")));

        AccountImp account = new AccountImp();
        account.addClients(Arrays.asList(user,company));

        if (demo) demoData(account);
        accountRepo.save(account);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATOR')")
    public void deleteAccount(User admin){
        accountRepo.delete(admin.getAccount());
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public void blockAccount(Long id){
        AccountImp account = accountRepo.findOne(id);
        account.setEnable(false);
    }

    public boolean accountStatus(Long id){
        return accountRepo.findOne(id).isEnable();
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public void removeAccount(Long id){
        accountRepo.delete(id);
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public Page<AccountImp> getAllAccounts(Pageable pageable){
        Page<AccountImp> page = accountRepo.findAll(pageable);
        return page;
    }

    @PreAuthorize("hasAuthority('ROLE_SYS_ADMINISTRATOR')")
    public AccountImp getAccountById(Long id){
        AccountImp account = accountRepo.findOne(id);
        return account;
    }

    protected void demoData(AccountImp account){

    }

    public boolean importClients(File file) throws InterruptedException {
        return virusResolver.scanedFileIsSafety(file);
    }

    public boolean importClients(byte[] file) throws Exception {
        ClamAvClient clamAvClient = new ClamAvClient();
        return clamAvClient.scan(file);
    }
}
