package ua.pp.jcrm.domain.entity;

import ua.pp.jcrm.domain.entity.impl.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited
@Getter
@Setter
public abstract class AbstractEntity
        extends AuditableImpl<User>
        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "OPTLOCK")
    private Long version;

    private boolean deleted = false;

    private boolean physicalRemoval = false;

    public AbstractEntity() {
    }
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
        if (getId() == null ||
                obj == null ||
                getClass() != obj.getClass())
            return false;
        AbstractEntity that = (AbstractEntity) obj;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() == null ? 0 : getId().hashCode();
    }
}
