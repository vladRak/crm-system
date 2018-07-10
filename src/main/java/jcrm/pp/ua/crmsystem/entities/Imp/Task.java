package jcrm.pp.ua.crmsystem.entities.Imp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jcrm.pp.ua.crmsystem.listeners.event.RootAware;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Table(name = "task")
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Audited
@Data
public class Task extends BaseEntityImp implements RootAware<BaseBusinessObjImp>, Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String taskName;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date taskDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "created_for_id")
    private BaseBusinessObjImp createdFor;

    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
    @JoinColumn(name = "account")
    private AccountImp account;

    public Task() {
    }

    @Override
    public BaseBusinessObjImp root() {
        return createdFor;
    }

    public void setCreatedFor(BaseBusinessObjImp baseBusinessObj){
        setCreatedFor(baseBusinessObj,true);
    }

    void setCreatedFor(BaseBusinessObjImp baseBusinessObj, boolean add){
        this.createdFor = baseBusinessObj;
        if (baseBusinessObj != null && add){
            createdFor.addTask(this,false);
        }
    }

    @Transient
    public boolean isNew() {
        if (id == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        Task that = (Task) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}