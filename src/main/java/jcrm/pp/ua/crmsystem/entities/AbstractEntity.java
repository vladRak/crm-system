package jcrm.pp.ua.crmsystem.entities;

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
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Audited
@Data
public abstract class AbstractEntity extends AbstractAuditable<User, Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version @Column(name="OPTLOCK")
    private Long versionNum = 0L;

    private boolean deleted = false;

    private boolean physicalRemoval = false;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (id == null || obj == null || getClass() != obj.getClass())
//            return false;
//        AbstractEntity that = (AbstractEntity) obj;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id == null ? 0 : id.hashCode();
//    }
}
