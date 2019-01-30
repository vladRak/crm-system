package jcrm.pp.ua.crmsystem.repositories;

import jcrm.pp.ua.crmsystem.entities.EntityWithRevision;
import jcrm.pp.ua.crmsystem.entities.impl.Company;
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
public class CompanyRevisionsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CompanyRepo companyRepo;

    public List<EntityWithRevision<Company>> listCompanyRevisions(Long id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Company companyObject = companyRepo.findOne(id);

        List<Number> revisions = auditReader.getRevisions(Company.class, id);

        List<EntityWithRevision<Company>> companyRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            Company companyRevision = auditReader.find(Company.class, id, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            companyRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), companyRevision));
        }

        return companyRevisions;
    }

}
