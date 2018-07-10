//package jcrm.pp.ua.crmsystem.services.imp;
//
//import jcrm.pp.ua.crmsystem.customClasses.CompanyPatchRequest;
//import jcrm.pp.ua.crmsystem.customClasses.ContactPatchRequest;
//
//
//import jcrm.pp.ua.crmsystem.entities.*;
//
//import jcrm.pp.ua.crmsystem.repositories.*;
//
//import jcrm.pp.ua.crmsystem.services.ContactService;
//
//import org.hibernate.PessimisticLockException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
//
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.OptimisticLockException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//@Service
//public class ContactServiceImp implements ContactService{
//
//
////    @Autowired
////    private SessionFactory sessionFactory;
////
////    @Autowired
////    EntityManagerFactory entityManagerFactory;
//
//
//    @Autowired
//    private ContactRepository contactRepository;
//    @Autowired
//    private CompanyRepository companyRepository;
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Autowired
//    private BaseClientRepo baseClientRepo;
//
//    @Autowired
//    Client2Repo client2Repo;
//
//
//
//    public ContactServiceImp() {
//    }
//
//    @Override
//    public Page<Contact> getAllContacts(Pageable pageable) {
//        Page<Contact> page = contactRepository.findAll(pageable);
//        return page;
//    }
//
//    @Override
//    public Contact getContactById(long id) {
//        Contact contact = contactRepository.findOne(id);
//        return contact;
//    }
//
//    @Override
//    public Page<Company> getAllCompanies(Pageable pageable) {
//        Page<Company> page = companyRepository.findAll(pageable);
//        return page;
//    }
//
//    @Override
//    public Company getCompanyById(long id) {
//        Company company = companyRepository.findOne(id);
//        return company;
//    }
//
//    @Override
//    public void addContact (Contact contact) {
//        //Session session = sessionFactory.openSession();
//        clientRepository.save(new Client(contact));
//    if (contact.getCompany() != null) {
//        Company company = contact.getCompany();
//        clientRepository.save(new Client(company));
//        }
//    }
//
//    @Override
//    public void addCompany(Company company) {
//
//        if(company.getContacts().size() > 0){
//        for (Contact c: company.getContacts()){
//            c.setClient(new Client());
//        }
//            clientRepository.save(new Client(company));
//        }else clientRepository.save(new Client(company));
//    }
//
//    @Override
//    public void removeContactById(long id) {
//        contactRepository.delete(id);
//    }
//
//    @Override
//    public void removeCompanyById(long id) {
//        companyRepository.delete(id);
//    }
//
////    @Override
////    public void updateContact(Contact contact) {
////        if(contact.getCompany() == null){
////            Contact contactTemp = contactRepository.getOne(contact.getId());
////            Client client = contactTemp.getClient();
////            client.setContact(contact);
////            clientRepository.save(client);
////        }else if (contact.getCompany().getId() == null){    //////
////            addContact(contact);
////        }else contactRepository.save(contact);
////    }
//
//    @Override
//    public void updateContact(Contact contact, Long id) {
//
//        Contact contactTemp = contactRepository.getOne(id);
//        Client client = contactTemp.getClient();
//        client.setContact(contact);
//        clientRepository.save(client);
//
//    }
//
//    @Override
//    public void updateCompany(Company company, Long id) {
//        Company companyTemp = companyRepository.getOne(id);
//        Client client = companyTemp.getClient();
//        client.setCompany(company);
//        clientRepository.save(client);
////        List<Contact> list = new ArrayList<>();
////        if (company.getContacts().size() != 0){
////            list.addAll(company.getContacts());}
////        clientRepository.save(client);
////        if (list.size() > 0){
////            for (Contact c: list) {
////                if (c.getId() == null){       //////
////                    c.setCompany(company);
////                    clientRepository.save(new Client(c));}
////                else return;
////            }}
//    }
//
////    @Override
////    public void updateCompany(Company company) {
////        Company companyTemp = companyRepository.getOne(company.getId());
////        Client client = companyTemp.getClient();
////        client.setCompany(company);
////        List<Contact> list = new ArrayList<>();
////        if (company.getContacts().size() != 0){
////         list.addAll(company.getContacts());}
////        clientRepository.save(client);
////        if (list.size() > 0){
////        for (Contact c: list) {
////          if (c.getId() == null){       //////
////              c.setCompany(company);
////              clientRepository.save(new Client(c));}
////          else return;
////        }}
////    }
//
//    @Override
//    public Page<Client> findPaginated(Pageable pageable) {
//        Page<Client> page = clientRepository.findAll(pageable);
//        return page;
//    }
//
//    @Override
//    public void patchContact(ContactPatchRequest patchRequest, Long id) {
//        Map<String,Object> fields = patchRequest.getFields();
//
////        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
////        Session session = sessionFactory.openSession();
////        session.beginTransaction();
////        Contact contactToPatch = session.get(Contact.class, id);
//        Contact contactToPatch = contactRepository.getOne(id);
//
//        //session.refresh(contactToPatch);
////        entityManager.getTransaction().begin();
////        Contact contactToPatch = entityManager.f;
//
//        final String ADDRESSES_KEY = "addresses";
//        final String COMPANY_KEY = "company";
//        final String NAME_KEY = "fullname";
//        final String EMAILS_KEY = "emails";
//        final String LEADS_KEY = "leads";
//        final String PHONES_KEY = "phones";
//        final String POSITION_KEY = "position";
//        final String TASKS_KEY = "tasks";
//
//        for (Map.Entry<String,Object> entry : fields.entrySet()) {
//            if (entry.getKey().equals(ADDRESSES_KEY)){contactToPatch.addAddress((List<Address>) entry.getValue());}
//            else if (entry.getKey().equals(COMPANY_KEY)){contactToPatch.setCompany((Company) entry.getValue());}
//            else if (entry.getKey().equals(NAME_KEY)){contactToPatch.setFullName((Name) entry.getValue());}
//            else if (entry.getKey().equals(EMAILS_KEY)){contactToPatch.addEmails((List<Email>) entry.getValue());}
//            else if (entry.getKey().equals(LEADS_KEY)){contactToPatch.addLeads((List<Lead>) entry.getValue());}
//            else if (entry.getKey().equals(PHONES_KEY)){contactToPatch.addPhones((List<Phone>) entry.getValue());}
//            else if (entry.getKey().equals(POSITION_KEY)){contactToPatch.setPosition((String) entry.getValue());}
//            else if (entry.getKey().equals(TASKS_KEY)){contactToPatch.addTasks((List<Task>) entry.getValue());}
//            else return;
//        }
//
//        try{
//            contactRepository.saveAndFlush(contactToPatch);
//        }catch (OptimisticLockException e){e.printStackTrace();}
//
//
//
//
//
////        entityManager.merge(contactToPatch);
////
////
////        entityManager.getTransaction().commit();
////        entityManager.flush();
////        entityManager.close();
////        entityManager.clear();
//
////        session.merge(contactToPatch);
////        //session.saveOrUpdate(contactToPatch);
////        session.flush();
////        session.getTransaction().commit();
////
////        session.close();
////        contactRepository.save(contactToPatch);
//
//
//    }
//
//    @Override
//    public void patchCompany(CompanyPatchRequest patchRequest, Long id) {
//        Map<String,Object> fields = patchRequest.getFields();
//
//        Company companyToPatch = companyRepository.getOne(id);
//
//        final String NAME_KEY = "fullname";
//        final String ADDRESSES_KEY = "addresses";
//        final String CONTACTS_KEY = "contacts";
//        final String EMAILS_KEY = "emails";
//        final String LEADS_KEY = "leads";
//        final String PHONES_KEY = "phones";
//        final String TASKS_KEY = "tasks";
//
//        for (Map.Entry<String,Object> entry : fields.entrySet()) {
//            if (entry.getKey().equals(ADDRESSES_KEY)){companyToPatch.addAddress((List<Address>) entry.getValue());}
//            if (entry.getKey().equals(CONTACTS_KEY)){companyToPatch.addContacts((List<Contact>) entry.getValue());}
//            if (entry.getKey().equals(NAME_KEY)){companyToPatch.setFullname((String) entry.getValue());}
//            if (entry.getKey().equals(EMAILS_KEY)){companyToPatch.addEmails((List<Email>) entry.getValue());}
//            if (entry.getKey().equals(LEADS_KEY)){companyToPatch.addLeads((List<Lead>) entry.getValue());}
//            if (entry.getKey().equals(PHONES_KEY)){companyToPatch.addPhones((List<Phone>) entry.getValue());}
//            if (entry.getKey().equals(TASKS_KEY)){companyToPatch.addTasks((List<Task>) entry.getValue());}
//        }
//
//        try{
//            companyRepository.saveAndFlush(companyToPatch);
//        }catch (OptimisticLockException e){e.printStackTrace();}
//
//    }
//
//    @Override
//    public Page<Client> searchClients(String search, Pageable pageable) {
//        Page<Client> page = clientRepository.search(search,pageable);
//        return page;
//    }
//
//    @Override
//    public Page<Contact> searchContacts(String search, Pageable pageable) {
//        Page<Contact> page = contactRepository.search(search,pageable);
//        return page;
//    }
//
//    @Override
//    public Page<Company> searchCompanies(String search, Pageable pageable) {
//        Page<Company> page = companyRepository.search(search,pageable);
//        return page;
//    }
//
//    @Override
//    public void addContact2(Client2 client2) throws InterruptedException {
//        client2Repo.save(client2);
//        List<Contact2> list = baseClientRepo.search("Contact2");
//
//    }
//
//    //        int start = pageable.getOffset();
////        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
//}
