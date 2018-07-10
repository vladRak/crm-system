package jcrm.pp.ua.crmsystem.entities;

import lombok.Data;
import org.hibernate.envers.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@RevisionEntity(value = RevisionListener.class)
@Table(name = "revisions_entity")
@Data
public class RevisionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @RevisionNumber
    private Long revisionId;

    @RevisionTimestamp
    private Date revisionDate;

    public RevisionsEntity(Long revisionId, Date revisionDate) {
        this.revisionId = revisionId;
        this.revisionDate = revisionDate;
    }

    public RevisionsEntity() {
    }
}
