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
@Table(name = "phone")
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("phone")
//@Audited
@Data
public class Phone extends BaseContactInfo implements RootAware<BaseClientImpl> {

    private static final long serialVersionUID = 1L;

    private String phone;

    @ManyToOne(cascade = {PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImpl client;

    public Phone() {
    }

    @Override
    public BaseClientImpl root() {
        return client;
    }
}
