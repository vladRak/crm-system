package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.entities.Task;
import jcrm.pp.ua.crmsystem.repositories.TaskRepo;
import jcrm.pp.ua.crmsystem.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImp implements TaskService{

    @Autowired
    TaskRepo taskRepo;

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        Page<Task> page = taskRepo.findAll(pageable);
        return page;
    }

    @Override
    public Task getTaskById(long id) {
        return taskRepo.findOne(id);
    }

    @Override
    public void addTask(Task task) {
        taskRepo.save(task);
    }

    @Override
    public void removeTask(long id) {
        taskRepo.delete(id);
    }

    @Override
    public void updateTask(Task task) {
        taskRepo.save(task);
    }


}
