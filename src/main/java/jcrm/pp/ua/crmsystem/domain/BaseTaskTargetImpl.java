package jcrm.pp.ua.crmsystem.domain;

import jcrm.pp.ua.crmsystem.domain.entity.Task;
import jcrm.pp.ua.crmsystem.domain.interfaces.BaseTaskTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "base_task_target")
@EqualsAndHashCode(callSuper = true)
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@Audited
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseTaskTargetImpl extends AbstractAccountContent implements BaseTaskTarget {

    private static final long serialVersionUID = 1L;

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "createdFor",
            orphanRemoval = true)
    private List<Task> tasks;

//    public BaseTaskTargetImpl(
//            Long id, Long versionNum,
//            boolean deleted, boolean physicalRemoval,
//            Account account, List<Task> tasks) {
//        super(id, versionNum, deleted, physicalRemoval, account);
//        this.tasks = tasks;
//    }
























    public void setTasks(List<Task> tasks) {
        if (tasks != null) addTasks(tasks);
    }

    public void addTasks(List<Task> tasks) {
        for (Task t : tasks) {
            addTask(t, true);
        }
    }

    public void addTask(Task task) {
        addTask(task, true);
    }

    public void addTask(Task task, boolean set) {
        if (task != null) {
            if (getTasks().contains(task)) {
                getTasks().set(getTasks().indexOf(task), task);
            } else {
                getTasks().add(task);
            }
            if (set) {
                task.setCreatedFor(this, false);
            }
        }
    }

    public void removeTask(Task task) {
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
}
