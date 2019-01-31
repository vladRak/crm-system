package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.domain.*;
import jcrm.pp.ua.crmsystem.domain.entity.*;
import jcrm.pp.ua.crmsystem.repositories.*;
import jcrm.pp.ua.crmsystem.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImp implements HistoryService {

    @Autowired
    CompanyRevisionsRepo companyRevisionsRepo;
    @Autowired
    ContactRevisionsRepo contactRevisionsRepo;
    @Autowired
    LeadRevisionsRepo leadRevisionsRepo;
    @Autowired
    TaskRevisionsRepo taskRevisionsRepo;
    @Autowired
    UserRevisionsRepo userRevisionsRepo;

    @Override
    public List<EntityWithRevision<Company>> findCompanyRevisions(Long id) {
        return companyRevisionsRepo.listCompanyRevisions(id);
    }

    @Override
    public List<EntityWithRevision<Contact>> findContactRevisions(Long id) {
        return contactRevisionsRepo.listContactRevisions(id);
    }

    @Override
    public List<EntityWithRevision<Lead>> findLeadRevisions(Long id) {
        return leadRevisionsRepo.listLeadRevisions(id);
    }

    @Override
    public List<EntityWithRevision<Task>> findTaskRevisions(Long id) {
        return taskRevisionsRepo.listTaskRevisions(id);
    }

    @Override
    public List<EntityWithRevision<User>> findUserRevisions(Long id) {
        return userRevisionsRepo.listUserRevisions(id);
    }
}
