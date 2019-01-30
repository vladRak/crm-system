package jcrm.pp.ua.crmsystem.entities.impl;

import jcrm.pp.ua.crmsystem.entities.AbstractEntity;
import jcrm.pp.ua.crmsystem.entities.BaseClient;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "account")
//@OptimisticLocking(type = VERSION)
@EqualsAndHashCode(callSuper = false)
//@Audited
@Setter
@Getter
public class Account extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private boolean enable = true;

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account")
    private List<User> users;

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account")
    private List<BaseClient> clients;

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account")
    private List<Lead> leads;

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account")
    private List<Task> tasks;

//    @Builder
//    public Account(
//            Long id, Long versionNum,
//            boolean deleted, boolean physicalRemoval,
//            boolean enable, List<User> users,
//            List<BaseClient> clients, List<Lead> leads,
//            List<Task> tasks) {
//        super(id, versionNum, deleted, physicalRemoval);
//        this.enable = enable;
//        this.users = users;
//        this.clients = clients;
//        this.leads = leads;
//        this.tasks = tasks;
//    }


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
