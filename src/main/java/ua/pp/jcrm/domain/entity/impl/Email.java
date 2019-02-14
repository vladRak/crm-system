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
@Table(name = "email")
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("email")
//@Audited
@Data
public class Email extends BaseContactInfo implements RootAware<BaseClientImpl> {

    private static final long serialVersionUID = 1L;

    private String email;

    @ManyToOne(cascade = {PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImpl client;

    @Override
    public BaseClientImpl root() {
        return client;
    }
}
