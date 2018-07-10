package jcrm.pp.ua.crmsystem.entities.Imp;

import jcrm.pp.ua.crmsystem.entities.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Audited
@Data
public class BaseEntityImp extends AbstractAuditable<User, Long> implements BaseEntity, Serializable {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version @Column(name="optlock")
    private long version = 0L;

    private static final long serialVersionUID = 1L;

    private boolean isDeleted = false;

    private boolean physicalRemoval = false;

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        BaseEntityImp that = (BaseEntityImp) obj;
        return id.equals(that.id);
    }

    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
