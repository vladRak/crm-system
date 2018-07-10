package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.EntityWithRevision;
import jcrm.pp.ua.crmsystem.entities.Imp.Task;
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
public class TaskRevisionsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TaskRepo taskRepo;

    public List<EntityWithRevision<Task>> listTaskRevisions(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Task taskObject = taskRepo.findOne(id);

        List<Number> revisions = auditReader.getRevisions(Task.class, id);

        List<EntityWithRevision<Task>> taskRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            Task taskRevision = auditReader.find(Task.class, id, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            taskRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), taskRevision));
        }

        return taskRevisions;
    }
}
