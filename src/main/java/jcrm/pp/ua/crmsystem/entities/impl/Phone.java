package jcrm.pp.ua.crmsystem.entities.impl;

import jcrm.pp.ua.crmsystem.entities.AbstractAccountContent;
import jcrm.pp.ua.crmsystem.entities.BaseClient;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Phone extends AbstractAccountContent implements RootAware<BaseClient> {

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
}
