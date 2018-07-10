package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.EntityWithRevision;
import jcrm.pp.ua.crmsystem.entities.Imp.Lead;
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
public class LeadRevisionsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LeadRepo leadRepo;

    public List<EntityWithRevision<Lead>> listLeadRevisions(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Lead leadObject = leadRepo.findOne(id);

        List<Number> revisions = auditReader.getRevisions(Lead.class, id);

        List<EntityWithRevision<Lead>> leadRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            Lead leadRevision = auditReader.find(Lead.class, id, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            leadRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), leadRevision));
        }

        return leadRevisions;
    }



}
