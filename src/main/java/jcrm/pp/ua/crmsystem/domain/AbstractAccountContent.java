package jcrm.pp.ua.crmsystem.domain;

import jcrm.pp.ua.crmsystem.domain.entity.Account;
import jcrm.pp.ua.crmsystem.domain.interfaces.AccountContent;
import lombok.*;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
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
//@NoArgsConstructor
//@Audited
@Getter
@Setter
public abstract class AbstractAccountContent extends AbstractEntity implements AccountContent {

    private static final long serialVersionUID = 1L;

    @NonNull
    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "account")
    private Account account;

    public AbstractAccountContent() {
    }

//    public AbstractAccountContent(
//            Long versionNum,
//            boolean deleted, boolean physicalRemoval,
//            Account account) {
//        super(versionNum, deleted, physicalRemoval);
//        this.account = account;
//    }
}
