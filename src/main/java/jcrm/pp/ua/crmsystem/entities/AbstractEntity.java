package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.entities.impl.User;
import lombok.Data;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited
@Data
public abstract class AbstractEntity
        extends AbstractAuditable<User, Long>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Version
    @Column(name = "OPTLOCK")
    private Long version;

    private boolean deleted = false;

    private boolean physicalRemoval = false;

//    public AbstractEntity() {
//    }
//
//    public AbstractEntity(Long versionNum, boolean deleted, boolean physicalRemoval) {
//        this.versionNum = versionNum;
//        this.deleted = deleted;
//        this.physicalRemoval = physicalRemoval;
//    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null ||
                obj == null ||
                getClass() != obj.getClass())
            return false;
        AbstractEntity that = (AbstractEntity) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
