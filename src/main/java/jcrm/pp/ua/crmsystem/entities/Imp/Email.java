package jcrm.pp.ua.crmsystem.entities.Imp;

import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "email")
@EqualsAndHashCode(callSuper = false)
@Audited
@Data
public class Email extends BaseEntityImp implements RootAware<BaseClientImp>, Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImp client;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
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
        Email that = (Email) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
