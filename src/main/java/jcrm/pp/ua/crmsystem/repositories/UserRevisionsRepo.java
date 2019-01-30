package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.EntityWithRevision;
import jcrm.pp.ua.crmsystem.entities.impl.User;
import jcrm.pp.ua.crmsystem.entities.RevisionsEntity;
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
public class UserRevisionsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    public List<EntityWithRevision<User>> listUserRevisions(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        User userObject = userRepo.findOne(id);

        List<Number> revisions = auditReader.getRevisions(User.class, id);

        List<EntityWithRevision<User>> userRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            User userRevision = auditReader.find(User.class, id, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            userRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), userRevision));
        }

        return userRevisions;
    }
}
