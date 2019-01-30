package jcrm.pp.ua.crmsystem.entities.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jcrm.pp.ua.crmsystem.entities.AbstractAccountContent;
import jcrm.pp.ua.crmsystem.entities.BaseTaskTarget;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "task")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited
@Data
public class Task extends AbstractAccountContent implements RootAware<BaseTaskTarget> {

    private static final long serialVersionUID = 1L;

    private String task;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date taskDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "created_for_id")
    private BaseTaskTarget createdFor;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "account")
    private Account account;

    @Override
    public BaseTaskTarget root() {
        return createdFor;
    }

    public void setCreatedFor(BaseTaskTarget baseBusinessObj){
        setCreatedFor(baseBusinessObj,true);
    }

    public void setCreatedFor(BaseTaskTarget baseBusinessObj, boolean add){
        this.createdFor = baseBusinessObj;
        if (baseBusinessObj != null && add){
            createdFor.addTask(this,false);
        }
    }

    @Transient
    public boolean isNew() {
        if (super.getId() == null) {
            return true;
        } else {
            return false;
        }
    }
}
