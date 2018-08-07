package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.customClasses.CompanyPatchRequest;
import jcrm.pp.ua.crmsystem.customClasses.ContactPatchRequest;
import jcrm.pp.ua.crmsystem.entities.Imp.BaseClientImp;
import jcrm.pp.ua.crmsystem.entities.Imp.Company;
import jcrm.pp.ua.crmsystem.entities.Imp.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    boolean importClients(byte[] file) throws Exception;
    Page<BaseClientImp> getAllClients(Pageable pageable);
    Page<Contact> getAllContacts(Pageable pageable);
    Page<Company> getAllCompanies(Pageable pageable);
    Contact getContactById(Long id);
    Company getCompanyById(Long id);
    void addContact(Contact contact) throws InterruptedException;
    void addCompany(Company company) throws InterruptedException;
    void removeContactById(Long id);
    void removeCompanyById(Long id);
    void updateContact(Contact contact, Long id);
    void updateCompany(Company company, Long id);
    void patchContact(ContactPatchRequest patchRequest, Long id);
    void patchCompany(CompanyPatchRequest patchRequest, Long id);
    Page<BaseClientImp> searchClients(String search, Pageable pageable);
    Page<Contact> searchContacts(String search, Pageable pageable);
    Page<Company> searchCompanies(String search, Pageable pageable);

}
