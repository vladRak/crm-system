package jcrm.pp.ua.crmsystem.entities;

import java.util.List;

public interface Account {
    List<BaseClient> getUsers();
    List<BaseBusinessObj> getLeads();
    List<BaseClient> getClients();
    List<BaseEntity> getTasks();
}
