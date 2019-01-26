package jcrm.pp.ua.crmsystem.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import static javax.persistence.CascadeType.*;

@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Audited
@Data
public abstract class AbstractBusinessObj extends AbstractEntity {

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "account")
    private Account account = new Account();
}
