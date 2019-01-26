package jcrm.pp.ua.crmsystem.services;

import jcrm.pp.ua.crmsystem.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Page<Task> getAllTasks(Pageable pageable);
    Task getTaskById(long id);
    void addTask(Task task);
    void removeTask(long id);
    void updateTask(Task task);
}

