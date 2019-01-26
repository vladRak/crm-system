package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "address")

//@ToString(exclude = "client")
@EqualsAndHashCode(callSuper = true)
@Audited
@Data
public class Address extends AbstractEntity implements RootAware<BaseClient> {
//    @Version
//    private long version = 0L;

    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    private String country;
    private String city;
    private String street;
    private String building;
    private String room;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    //@Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private BaseClient client;

    public Address() {
    }

    @Override
    public BaseClient root() {
        return client;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (id == null || obj == null || getClass() != obj.getClass())
//            return false;
//        Address that = (Address) obj;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id == null ? 0 : id.hashCode();
//    }
}
