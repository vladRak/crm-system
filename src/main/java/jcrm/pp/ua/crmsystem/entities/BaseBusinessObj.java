package jcrm.pp.ua.crmsystem.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
//@DiscriminatorColumn(
//        name="b_o_type",
//        discriminatorType= DiscriminatorType.STRING)
//@DiscriminatorValue("baseBO")
@Table(name = "base_business_obj")
@EqualsAndHashCode(callSuper = true)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Audited
@Data
public class BaseBusinessObj extends AbstractBusinessObj {

    private static final long serialVersionUID = 1L;

//    @Version
//    @Column(name="optlock")
//    private long version = 0L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "createdFor",
            orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

//    @ManyToOne(cascade = {CascadeType.PERSIST,MERGE})
//    @JoinColumn(name = "account")
//    private Account account = new Account();

    public void setTasks(List<Task> tasks){
        if(tasks!=null)   addTasks(tasks);
    }

    public void addTasks(List<Task> tasks){
        for (Task t: tasks) {
            addTask(t,true);
        }

    }

    public void addTask(Task task){
        addTask(task,true);
    }

    public void addTask(Task task, boolean set){
        if(task !=null){
            if (getTasks().contains(task)){
                getTasks().set(getTasks().indexOf(task),task);
            }else {
                getTasks().add(task);
            }
            if (set){
                task.setCreatedFor(this,false);
            }
        }
    }

    public void removeTask(Task task){
        getTasks().remove(task);
        task.setCreatedFor(null);
    }

    @Transient
    public boolean isNew() {
        if (super.getId() == null) {
            return true;
        } else {
            return false;
        }
    }



//    public void setAccount(Account account){
//        setAccount(account,true);
//    }
//
//    void setAccount(Account account, boolean add){
//        this.account = account;
//        if (account != null && add){
//            account.addBO(this,false);
//        }
//    }


//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (id == null || obj == null || getClass() != obj.getClass())
//            return false;
//        BaseBusinessObj that = (BaseBusinessObj) obj;
//        return id.equals(that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return id == null ? 0 : id.hashCode();
//    }
}
