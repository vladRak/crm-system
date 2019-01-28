package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "address")
//@ToString(exclude = "client")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Address extends AbstractAccountContent implements RootAware<BaseClient> {

    private static final long serialVersionUID = 1L;

    private String country;
    private String city;
    private String street;
    private String building;
    private String room;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClient client;

    @Override
    public BaseClient root() {
        return client;
    }
}
