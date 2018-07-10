package jcrm.pp.ua.crmsystem.entities.Imp;


import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.MERGE;


@Entity
@Table(name = "phone")
@EqualsAndHashCode(callSuper = false)
@Audited
@Data
public class Phone extends BaseEntityImp implements RootAware<BaseClientImp>, Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String phone;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImp client;

    public Phone() {
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
        Phone that = (Phone) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
