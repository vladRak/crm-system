package jcrm.pp.ua.crmsystem.entities.temp_test;

import jcrm.pp.ua.crmsystem.entities.AuditableImpl;
import jcrm.pp.ua.crmsystem.entities.IdGenerator;
import jcrm.pp.ua.crmsystem.entities.User;
import lombok.Data;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Data
public abstract class AbstractTemp extends AuditableImpl<User>
//        extends AbstractAuditable<User, Long>

        implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "OPTLOCK")
    private Long version;

}
