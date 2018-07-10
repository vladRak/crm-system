//package jcrm.pp.ua.crmsystem.services;
//
//import jcrm.pp.ua.crmsystem.customClasses.CompanyPatchRequest;
//import jcrm.pp.ua.crmsystem.customClasses.ContactPatchRequest;
//import jcrm.pp.ua.crmsystem.entities.*;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.List;
//
//
//public interface ContactService {
//    Page<Client>  findPaginated(Pageable pageable);
//    Page<Contact> getAllContacts(Pageable pageable);
//    Page<Company> getAllCompanies(Pageable pageable);
//    Contact getContactById(long id);
//    Company getCompanyById(long id);
//    void addContact(Contact contact) throws InterruptedException;
//    void addCompany(Company company) throws InterruptedException;
//    void removeContactById(long id);
//    void removeCompanyById(long id);
//    void updateContact(Contact contact, Long id);
//    void updateCompany(Company company, Long id);
//    void patchContact(ContactPatchRequest patchRequest, Long id);
//    void patchCompany(CompanyPatchRequest patchRequest, Long id);
//    Page<Client> searchClients(String search, Pageable pageable);
//    Page<Contact> searchContacts(String search, Pageable pageable);
//    Page<Company> searchCompanies(String search, Pageable pageable);
//
//
//    void addContact2(Client2 client2) throws InterruptedException;
//}
