package jcrm.pp.ua.crmsystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jcrm.pp.ua.crmsystem.domain.AbstractAccountContent;
import jcrm.pp.ua.crmsystem.domain.BaseTaskTargetImpl;
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
public class Task extends AbstractAccountContent implements RootAware<BaseTaskTargetImpl> {

    private static final long serialVersionUID = 1L;

    private String task;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date taskDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "created_for_id")
    private BaseTaskTargetImpl createdFor;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "account")
    private Account account;

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

    @Transient
    public boolean isNew() {
        if (super.getId() == null) {
            return true;
        } else {
            return false;
        }
    }
}
