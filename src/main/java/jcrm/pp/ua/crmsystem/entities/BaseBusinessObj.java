package jcrm.pp.ua.crmsystem.entities;


import jcrm.pp.ua.crmsystem.entities.Imp.Task;

import java.util.ArrayList;
import java.util.List;

public interface BaseBusinessObj extends BaseEntity{
    Long id = null;
    List<Task> tasks = new ArrayList<>();
    void addTask(Task task);
    void removeTask(Task task);
    boolean equals(Object obj);
    int hashCode();
}
