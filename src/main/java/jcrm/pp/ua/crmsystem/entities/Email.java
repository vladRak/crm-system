package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "email")
@EqualsAndHashCode(callSuper = true)
@Audited
@Data
public class Email extends AbstractEntity implements RootAware<BaseClient>, Serializable{

    private static final long serialVersionUID = 1L;

//    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClient client;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
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
//        Email that = (Email) obj;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id == null ? 0 : id.hashCode();
//    }
}
