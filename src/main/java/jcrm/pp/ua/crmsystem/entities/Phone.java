package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@EqualsAndHashCode(callSuper = true)
@Audited
@Data
public class Phone extends AbstractEntity implements RootAware<BaseClient> {

    private static final long serialVersionUID = 1L;

//    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    private String phone;

    @ManyToOne()//cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClient client;

    public Phone() {
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
//        Phone that = (Phone) obj;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id == null ? 0 : id.hashCode();
//    }
}
