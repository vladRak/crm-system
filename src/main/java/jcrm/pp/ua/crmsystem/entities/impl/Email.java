package jcrm.pp.ua.crmsystem.entities.impl;

import jcrm.pp.ua.crmsystem.entities.AbstractAccountContent;
import jcrm.pp.ua.crmsystem.entities.BaseClient;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "email")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Email extends AbstractAccountContent implements RootAware<BaseClient> {

    private static final long serialVersionUID = 1L;

    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClient client;

    @Override
    public BaseClient root() {
        return client;
    }
}
