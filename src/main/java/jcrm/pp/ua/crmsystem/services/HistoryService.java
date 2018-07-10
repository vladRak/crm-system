package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.entities.EntityWithRevision;
import jcrm.pp.ua.crmsystem.entities.Imp.*;

import java.util.List;

public interface HistoryService {

    List<EntityWithRevision<Company>> findCompanyRevisions(Long id);
    List<EntityWithRevision<Contact>> findContactRevisions(Long id);
    List<EntityWithRevision<Lead>> findLeadRevisions(Long id);
    List<EntityWithRevision<Task>> findTaskRevisions(Long id);
    List<EntityWithRevision<User>> findUserRevisions(Long id);
}
