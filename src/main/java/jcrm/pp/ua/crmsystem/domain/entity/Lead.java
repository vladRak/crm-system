package jcrm.pp.ua.crmsystem.domain.entity;


import jcrm.pp.ua.crmsystem.domain.BaseClientImplImpl;
import jcrm.pp.ua.crmsystem.domain.BaseTaskTargetImpl;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "lead")
//@DiscriminatorValue("lead")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Lead extends BaseTaskTargetImpl implements RootAware<BaseClientImplImpl> {

    private static final long serialVersionUID = 1L;

    private String leadName;

    private String leadStatus;

    private BigDecimal budget;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImplImpl client;

    @Override
    public BaseClientImplImpl root() {
        return client;
    }

    public Lead() {
    }

    public void setClient(BaseClientImplImpl client) {
        setClient(client, true);
    }

    public void setClient(BaseClientImplImpl client, boolean add) {
        this.client = client;
        if (client != null && add) {
            client.addLead(this, false);
        }
    }
}
