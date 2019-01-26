package jcrm.pp.ua.crmsystem.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "account")
@OptimisticLocking(type = OptimisticLockType.VERSION)
@EqualsAndHashCode(callSuper = false)
@Audited
@Data
public class Account extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private boolean enable = true;

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            mappedBy = "account")
    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            mappedBy = "account")
    private List<BaseClient> clients = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            mappedBy = "account")
    private List<Lead> leads = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY,
            mappedBy = "account")
    private List<Task> tasks = new ArrayList<>();


//    public void setUsers(List<User> users){
//        if(users!=null) addUsers(users);
//    }

    public void setClients(List<BaseClient> clients) {
        if (clients != null) addClients(clients);
    }

//    public void setLeads(List<Lead> leads){
//        if(leads !=null) addLeads(leads);
//    }

    public void setTasks(List<Task> tasks) {
        if (tasks != null) addTasks(tasks);
    }

    public void addUser(User user) {
        addUsers(Arrays.asList(user));
    }

    public void addUsers(List<User> users) {
        for (User u : users) {
            u.setAccount(this);
            this.users.add(u);
        }
    }

    public void addClient(BaseClient client) {
        addClients(Arrays.asList(client));
    }

    public void addClients(List<BaseClient> clients) {
        for (BaseClient c : clients) {
            c.setAccount(this);
            this.clients.add(c);
        }
    }

//    public void addLeads(List<Lead> leads){
//        for (Lead l : leads) {
//            l.setAccount(this);
//            this.leads.add(l);
//        }
//    }

    public void addTask(Task task) {
        addTasks(Arrays.asList(task));
    }

    public void addTasks(List<Task> tasks) {
        for (Task t : tasks) {
            t.setAccount(this);
            this.tasks.add(t);
        }
    }


    public void addUser(User user, boolean add) {

    }


    public void setLeads(List<Lead> leads) {

    }


    public void addLead(Lead lead) {

    }


    public void addLead(Lead lead, boolean add) {

    }


    public void addClient(BaseClient client, boolean add) {

    }


    public void addTask(Task task, boolean add) {

    }


    //    public void setTasks(List<Task> tasks){
//        if(tasks!=null)   addTasks(tasks);
//    }
//
//    public void addTasks(List<Task> tasks){
//        for (Task t: tasks) {
//            addTask(t,true);
//        }
//    }
//
//    public void addTask(Task task){
//        addTask(task,true);
//    }
//
//    void addTask(Task task, boolean set){
//        if(task !=null){
//            if (getTasks().contains(task)){
//                getTasks().set(getTasks().indexOf(task),task);
//            }else {
//                getTasks().add(task);
//            }
//            if (set){
//                task.setAccount(this,false);
//            }
//        }
//    }
//
//    public void removeTask(Task task){
//        getTasks().remove(task);
//        task.setAccount(null);
//    }


}
