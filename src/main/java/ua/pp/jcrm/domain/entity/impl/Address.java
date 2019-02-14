package ua.pp.jcrm.domain.entity.impl;

import ua.pp.jcrm.domain.entity.BaseClientImpl;
import ua.pp.jcrm.domain.entity.BaseContactInfo;
import ua.pp.jcrm.domain.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "address")
//@ToString(exclude = "client")
@DiscriminatorValue("address")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Address extends BaseContactInfo implements RootAware<BaseClientImpl> {

    private static final long serialVersionUID = 1L;

    private String country;
    private String city;
    private String street;
    private String building;
    private String room;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImpl client;

    @Override
    public BaseClientImpl root() {
        return client;
    }
}
