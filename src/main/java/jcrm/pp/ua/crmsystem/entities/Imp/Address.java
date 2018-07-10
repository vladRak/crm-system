package jcrm.pp.ua.crmsystem.entities.Imp;


import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.MERGE;


@Entity
@Table(name = "address")

//@ToString(exclude = "client")
@EqualsAndHashCode(callSuper = false)
@Audited
@Data
public class Address extends BaseEntityImp implements RootAware<BaseClientImp>, Serializable{
//    @Version
//    private long version = 0L;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String city;

    private String address;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    //@Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private BaseClientImp client;

    public Address() {
    }

    @Override
    public BaseClientImp root() {
        return client;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        Address that = (Address) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
