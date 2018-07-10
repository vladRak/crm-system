package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.customClasses.CompanyPatchRequest;
import jcrm.pp.ua.crmsystem.customClasses.ContactPatchRequest;
import jcrm.pp.ua.crmsystem.entities.BaseClient;
import jcrm.pp.ua.crmsystem.entities.Imp.*;
import jcrm.pp.ua.crmsystem.repositories.*;
import jcrm.pp.ua.crmsystem.services.ClientService;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    private BaseClientRepo baseClientRepo;
    @Autowired
    private ContactRepo contactRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public ClientServiceImp() {
    }

    @Override
    public Page<Contact> getAllContacts(Pageable pageable) {
        Page<Contact> page = baseClientRepo.findAllContacts(pageable);
        return page;
    }

    @Override
    public Contact getContactById(Long id) {
        Contact contact = baseClientRepo.findOneContacts(id);
        return contact;
    }

    @Override
    public Page<Company> getAllCompanies(Pageable pageable) {
        Page<Company> page = baseClientRepo.findAllCompanies(pageable);
        return page;
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = baseClientRepo.findOneCompany(id);
        return company;
    }

    @Override
    public void addContact (Contact contact) {
        baseClientRepo.save(contact);
    }

    @Override
    public void addCompany(Company company) {
        baseClientRepo.save(company);
    }

    @Override
    public void removeContactById(Long id) {
        Contact contact = contactRepo.getOne(id);
        contact.setDeleted(true);
        baseClientRepo.save(contact);
        //baseClientRepo.delete(id);
    }

    @Override
    public void removeCompanyById(Long id) {
        baseClientRepo.delete(id);
    }

    @Override
    public void updateContact(Contact contact, Long id) {
        //BaseClientImp contactTemp = baseClientRepo.getOne(id);
        contact.setId(id);
        baseClientRepo.save(contact);
    }

    @Override
    public void updateCompany(Company company, Long id) {
        //BaseClientImp contactTemp = baseClientRepo.getOne(id);
        company.setId(id);
        baseClientRepo.save(company);
    }

    @Override
    public Page<BaseClientImp> getAllClients(Pageable pageable) {
        Page<BaseClientImp> page = baseClientRepo.findAll(pageable);
        return page;
    }

    @Override
    public void patchContact(ContactPatchRequest patchRequest, Long id) {
        Map<String,Object> fields = patchRequest.getFields();

        Contact contactToPatch = (Contact) baseClientRepo.getOne(id);

        final String ADDRESSES_KEY = "addresses";
        final String COMPANY_KEY = "company";
        final String NAME_KEY = "fullname";
        final String EMAILS_KEY = "emails";
        final String LEADS_KEY = "leads";
        final String PHONES_KEY = "phones";
        final String POSITION_KEY = "position";
        final String TASKS_KEY = "tasks";

        for (Map.Entry<String,Object> entry : fields.entrySet()) {
            if (entry.getKey().equals(ADDRESSES_KEY)){contactToPatch.addAddresses((List<Address>) entry.getValue());}
            else if (entry.getKey().equals(COMPANY_KEY)){contactToPatch.setCompany((Company) entry.getValue());}
            else if (entry.getKey().equals(NAME_KEY)){contactToPatch.setFullName((Name) entry.getValue());}
            else if (entry.getKey().equals(EMAILS_KEY)){contactToPatch.addEmails((List<Email>) entry.getValue());}
            else if (entry.getKey().equals(LEADS_KEY)){contactToPatch.addLeads((List<Lead>) entry.getValue());}
            else if (entry.getKey().equals(PHONES_KEY)){contactToPatch.addPhones((List<Phone>) entry.getValue());}
            else if (entry.getKey().equals(POSITION_KEY)){contactToPatch.setPosition((String) entry.getValue());}
            else if (entry.getKey().equals(TASKS_KEY)){contactToPatch.addTasks((List<Task>) entry.getValue());}
            else return;
        }

        try{
            baseClientRepo.saveAndFlush(contactToPatch);
        }catch (OptimisticLockException e){e.printStackTrace();}
    }

    @Override
    public void patchCompany(CompanyPatchRequest patchRequest, Long id) {
        Map<String,Object> fields = patchRequest.getFields();

        Company companyToPatch = (Company) baseClientRepo.getOne(id);

        final String NAME_KEY = "fullname";
        final String ADDRESSES_KEY = "addresses";
        final String CONTACTS_KEY = "contacts";
        final String EMAILS_KEY = "emails";
        final String LEADS_KEY = "leads";
        final String PHONES_KEY = "phones";
        final String TASKS_KEY = "tasks";

        for (Map.Entry<String,Object> entry : fields.entrySet()) {
            if (entry.getKey().equals(ADDRESSES_KEY)){companyToPatch.addAddresses((List<Address>) entry.getValue());}
            if (entry.getKey().equals(CONTACTS_KEY)){companyToPatch.addContacts((List<Contact>) entry.getValue());}
            if (entry.getKey().equals(NAME_KEY)){companyToPatch.setFullName((String) entry.getValue());}
            if (entry.getKey().equals(EMAILS_KEY)){companyToPatch.addEmails((List<Email>) entry.getValue());}
            if (entry.getKey().equals(LEADS_KEY)){companyToPatch.addLeads((List<Lead>) entry.getValue());}
            if (entry.getKey().equals(PHONES_KEY)){companyToPatch.addPhones((List<Phone>) entry.getValue());}
            if (entry.getKey().equals(TASKS_KEY)){companyToPatch.addTasks((List<Task>) entry.getValue());}
        }

        try{
            baseClientRepo.saveAndFlush(companyToPatch);
        }catch (OptimisticLockException e){e.printStackTrace();}
    }

    @Override
    public Page<BaseClientImp> searchClients(String search, Pageable pageable) {
        Page<BaseClientImp> page =null; //baseClientRepo.searchByName(search,pageable);
        return page;
    }

    @Override
    public Page<Contact> searchContacts(String search, Pageable pageable) {
        Page<Contact> page = baseClientRepo.searchContactByName(search,pageable);
        return page;
    }

    @Override
    public Page<Company> searchCompanies(String search, Pageable pageable) {
        Page<Company> page = baseClientRepo.searchCompanyByName(search,pageable);
        return page;
    }

    public BaseClientImp findAuditByRevision(Long id, int revision) {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        List<Contact> list =  reader.createQuery().forRevisionsOfEntity(Contact.class, false,false).getResultList();
        Contact contact = reader.find(Contact.class, id, revision);

        AuditQuery auditQuery = reader.createQuery().forRevisionsOfEntity(Contact.class, false,false);
        auditQuery.add( AuditEntity.id().eq(id));

        List result =  auditQuery.getResultList();

//        if(result.size() > 0){
//            String queryString = "delete c from contact_AUD c where c.id = :id;";
//            entityManager.createNativeQuery(queryString).setParameter("id", 1L);
//        }

        return contact;
    }

    //        int start = pageable.getOffset();
//        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
}
