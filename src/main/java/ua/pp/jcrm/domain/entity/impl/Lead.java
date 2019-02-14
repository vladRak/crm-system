package ua.pp.jcrm.domain.entity.impl;


import ua.pp.jcrm.domain.entity.BaseClientImpl;
import ua.pp.jcrm.domain.entity.BaseTaskTargetImpl;
import ua.pp.jcrm.domain.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "lead")
@DiscriminatorValue("lead")
@EqualsAndHashCode(callSuper = true)
//@Audited
@Data
public class Lead extends BaseTaskTargetImpl implements RootAware<BaseClientImpl> {

    private static final long serialVersionUID = 1L;

    private String leadName;

    private String leadStatus;

    private BigDecimal budget;

    @ManyToOne(cascade = {CascadeType.PERSIST, MERGE})
    @JoinColumn(name = "client_id")
    private BaseClientImpl client;

    public Lead() {
    }

    @Override
    public BaseClientImpl root() {
        return client;
    }

    @Override
    protected void setAccount(Account account, boolean add) {
        if (nonNull(account) && add) {
            account.addLead(this, false);
        } else if (nonNull(this.account)) {
            this.account.getLeads().remove(this);
        }

        this.account = account;
    }

    public void setClient(BaseClientImpl client) {
        setClient(client, true);
    }

    public void setClient(BaseClientImpl client, boolean add) {
        this.client = client;
        if (client != null && add) {
            client.addLead(this, false);
        }
    }
}
