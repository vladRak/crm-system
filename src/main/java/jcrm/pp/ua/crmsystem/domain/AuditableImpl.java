package jcrm.pp.ua.crmsystem.domain;

import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.ClassUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AuditableImpl<U> implements Auditable<U, String> {

    @Transient
    private UUID generatedId;
    @Id
    private UUID id;
    @ManyToOne
    private U createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @ManyToOne
    private U lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public AuditableImpl() {
        this.generatedId = IdGenerator.createId();
        this.id = this.generatedId;
        System.out.println("GENERATED_ID: " + generatedId + "////////////////////");
    }

    public U getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedDate() {
        return null == this.createdDate ? null : new DateTime(this.createdDate);
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = null == createdDate ? null : createdDate.toDate();
    }

    public U getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public DateTime getLastModifiedDate() {
        return null == this.lastModifiedDate ? null : new DateTime(this.lastModifiedDate);
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
    }

    public String getId() {
        return this.id.toString();
    }

    protected void setId(String id) {
        this.id = UUID.fromString(id);
    }

    @Transient
    public boolean isNew() {
        if (id != null && generatedId.equals(id)) return true;
        else return false;
    }

    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), this.getId());
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!this.getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        } else {
            AbstractPersistable<?> that = (AbstractPersistable) obj;
            return null == this.getId() ? false : this.getId().equals(that.getId());
        }
    }

    public int hashCode() {
        int hashCode = 17;
        hashCode = hashCode + (null == this.getId() ? 0 : this.getId().hashCode() * 31);
        return hashCode;
    }
}
