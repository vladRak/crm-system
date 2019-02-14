package ua.pp.jcrm.domain.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ua.pp.jcrm.domain.entity.AbstractAccountContent;
import ua.pp.jcrm.domain.entity.BaseTaskTargetImpl;
import ua.pp.jcrm.domain.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

import static java.util.Objects.nonNull;
import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "task")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@DiscriminatorValue("task")
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited
@Data
public class Task extends AbstractAccountContent implements RootAware<BaseTaskTargetImpl> {

    private static final long serialVersionUID = 1L;

    private String task;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date taskDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "created_for_id")
    private BaseTaskTargetImpl createdFor;

    @Override
    public BaseTaskTargetImpl root() {
        return createdFor;
    }

    public void setCreatedFor(BaseTaskTargetImpl baseBusinessObj){
        setCreatedFor(baseBusinessObj,true);
    }

    public void setCreatedFor(BaseTaskTargetImpl baseBusinessObj, boolean add){
        this.createdFor = baseBusinessObj;
        if (baseBusinessObj != null && add){
            createdFor.addTask(this,false);
        }
    }

    @Override
    protected void setAccount(Account account, boolean add) {
        if (nonNull(account) && add) {
            account.addTask(this, false);
        } else if (nonNull(this.account)) {
            this.account.getTasks().remove(this);
        }

        this.account = account;
    }
}
