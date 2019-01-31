package jcrm.pp.ua.crmsystem.domain.entity;

import jcrm.pp.ua.crmsystem.domain.AbstractAccountContent;
import jcrm.pp.ua.crmsystem.domain.BaseClientImplImpl;
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
public class Email extends AbstractAccountContent implements RootAware<BaseClientImplImpl> {

    private static final long serialVersionUID = 1L;

    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImplImpl client;

    @Override
    public BaseClientImplImpl root() {
        return client;
    }
}
