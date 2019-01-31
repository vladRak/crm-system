package jcrm.pp.ua.crmsystem.domain.entity;

import jcrm.pp.ua.crmsystem.domain.AbstractAccountContent;
import jcrm.pp.ua.crmsystem.domain.BaseClientImplImpl;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "phone")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Phone extends AbstractAccountContent implements RootAware<BaseClientImplImpl> {

    private static final long serialVersionUID = 1L;

//    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    private String phone;

    @ManyToOne()//cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImplImpl client;

    public Phone() {
    }

    @Override
    public BaseClientImplImpl root() {
        return client;
    }
}
