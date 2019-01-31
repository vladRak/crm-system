package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.domain.EntityWithRevision;
import jcrm.pp.ua.crmsystem.domain.entity.Contact;
import jcrm.pp.ua.crmsystem.domain.RevisionsEntity;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class ContactRevisionsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContactRepo contactRepo;

    public List<EntityWithRevision<Contact>> listContactRevisions(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Contact contactObject = contactRepo.findOne(id);

        List<Number> revisions = auditReader.getRevisions(Contact.class, id);

        List<EntityWithRevision<Contact>> contactRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            Contact contactRevision = auditReader.find(Contact.class, id, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            contactRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), contactRevision));
        }

        return contactRevisions;
    }


}
