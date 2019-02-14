package ua.pp.jcrm.domain.entity.impl;

import ua.pp.jcrm.domain.entity.AbstractEntity;
import ua.pp.jcrm.domain.entity.BaseClientImpl;
import ua.pp.jcrm.domain.entity.BaseContactInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
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
            mappedBy = "account",
            orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account",
            orphanRemoval = true)
    private List<BaseClientImpl> clients = new ArrayList<>();

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account",
            orphanRemoval = true)
    private List<Lead> leads = new ArrayList<>();

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account",
            orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(
            cascade = ALL,
            fetch = LAZY,
            mappedBy = "account",
            orphanRemoval = true)
    private List<BaseContactInfo> contactsInfo = new ArrayList<>();

    //    @Builder
//    public Account(
//            Long id, Long versionNum,
//            boolean deleted, boolean physicalRemoval,
//            boolean enable, List<User> users,
//            List<BaseClientImpl> clients, List<Lead> leads,
//            List<Task> tasks) {
//        super(id, versionNum, deleted, physicalRemoval);
//        this.enable = enable;
//        this.users = users;
//        this.clients = clients;
//        this.leads = leads;
//        this.tasks = tasks;
//    }


    public List<User> getUsers() {
        if (isNull(users)) return emptyList();
        else return users;
    }

    public List<BaseClientImpl> getClients() {
        if (isNull(clients)) return emptyList();
        else return clients;
    }

    public List<Lead> getLeads() {
        if (isNull(leads)) return emptyList();
        else return leads;
    }

    public List<Task> getTasks() {
        if (isNull(tasks)) return emptyList();
        else return tasks;
    }

    public List<BaseContactInfo> getContactsInfo() {
        if (isNull(contactsInfo)) return emptyList();
        else return contactsInfo;
    }

    /**
    * USER
    */

    public void addUser(User user) {
        addUser(user, true);
    }

    public void addUser(User user, boolean set) {
        if (user != null) {
            if (getUsers().contains(user)) {
                getUsers().set(getUsers().indexOf(user), user);
            } else {
                getUsers().add(user);
            }
            if (set) {
                user.setAccount(this, false);
            }
        }
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setAccount(null);
    }

    /**
    * CLIENTS
    */

    public void addClient(BaseClientImpl baseClient) {
        addClient(baseClient, true);
    }

    public void addClient(BaseClientImpl baseClient, boolean set) {
        if (baseClient != null) {
            if (getClients().contains(baseClient)) {
                getClients().set(getClients().indexOf(baseClient), baseClient);
            } else {
                getClients().add(baseClient);
            }
            if (set) {
                baseClient.setAccount(this, false);
            }
        }
    }

    public void removeContactInfo(BaseClientImpl baseClient) {
        getClients().remove(baseClient);
        baseClient.setAccount(null);
    }

    /**
     * LEADS
     */

    public void addLead(Lead lead) {
        addLead(lead, true);
    }

    public void addLead(Lead lead, boolean set) {
        if (lead != null) {
            if (getLeads().contains(lead)) {
                getLeads().set(getLeads().indexOf(lead), lead);
            } else {
                getLeads().add(lead);
            }
            if (set) {
                lead.setAccount(this, false);
            }
        }
    }

    public void removeLead(Lead lead) {
        getLeads().remove(lead);
        lead.setAccount(null);
    }


    /**
     * TASKS
     */

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
                task.setAccount(this, false);
            }
        }
    }

    public void removeTask(Task task) {
        getTasks().remove(task);
        task.setAccount(null);
    }

    /**
     * CONTACT_INFO
     */

    public void addContactInfo(BaseContactInfo contactInfo) {
        addContactInfo(contactInfo, true);
    }

    public void addContactInfo(BaseContactInfo contactInfo, boolean set) {
        if (contactInfo != null) {
            if (getContactsInfo().contains(contactInfo)) {
                getContactsInfo().set(getContactsInfo().indexOf(contactInfo), contactInfo);
            } else {
                getContactsInfo().add(contactInfo);
            }
            if (set) {
                contactInfo.setAccount(this, false);
            }
        }
    }

    public void removeContactInfo(BaseContactInfo contactInfo) {
        getContactsInfo().remove(contactInfo);
        contactInfo.setAccount(null);
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
