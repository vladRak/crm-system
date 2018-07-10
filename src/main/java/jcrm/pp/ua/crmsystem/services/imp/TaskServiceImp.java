package jcrm.pp.ua.crmsystem.services.imp;

import jcrm.pp.ua.crmsystem.entities.Imp.Task;
import jcrm.pp.ua.crmsystem.repositories.TaskRepo;
import jcrm.pp.ua.crmsystem.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService{

    @Autowired
    TaskRepo taskRepo;

    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        List<Task> list = (List<Task>) taskRepo.findAll();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : (start + pageable.getPageSize());
        Page<Task> pages = new PageImpl(list.subList(start, end), pageable, list.size());

        return pages;
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

    @Override
    public Task getTaskById(long id) {
        Task task = taskRepo.findOne(id);
        return task;
    }
}
