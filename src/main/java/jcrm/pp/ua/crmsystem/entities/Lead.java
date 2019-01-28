package jcrm.pp.ua.crmsystem.entities;


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
public class Lead extends BaseTaskTarget implements RootAware<BaseClient> {

    private static final long serialVersionUID = 1L;

    private String leadName;

    private String leadStatus;

    private BigDecimal budget;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClient client;

    @Override
    public BaseClient root() {
        return client;
    }

    public Lead() {
    }

    public void setClient(BaseClient client) {
        setClient(client, true);
    }

    public void setClient(BaseClient client, boolean add) {
        this.client = client;
        if (client != null && add) {
            client.addLead(this, false);
        }
    }
}
