package jcrm.pp.ua.crmsystem.entities;

import jcrm.pp.ua.crmsystem.entities.Imp.User;

import java.util.List;

public interface Account {
    List<BaseClient> getUsers();
    List<BaseBusinessObj> getLeads();
    List<BaseClient> getClients();
    List<BaseEntity> getTasks();
    void setEnable(boolean var);
    boolean isEnable();
    void delete(User admin);
}
