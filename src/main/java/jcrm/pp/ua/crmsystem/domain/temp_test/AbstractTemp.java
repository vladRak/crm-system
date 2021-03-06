package jcrm.pp.ua.crmsystem.domain.temp_test;

import jcrm.pp.ua.crmsystem.domain.AuditableImpl;
import lombok.Data;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Data
public abstract class AbstractTemp extends AuditableImpl<UserTemp>
//        extends AbstractAuditable<User, Long>

        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "OPTLOCK")
    private Long version;

}
