package jcrm.pp.ua.crmsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import jcrm.pp.ua.crmsystem.dto.TaskDTO;
import jcrm.pp.ua.crmsystem.entities.Imp.Task;
import jcrm.pp.ua.crmsystem.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(
        value = "tasks",
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE},
        consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.TEXT_PLAIN_VALUE,MediaType.ALL_VALUE})
public class TaskController {
    final ModelMapper modelMapper = new ModelMapper();
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody Page<TaskDTO> findAllTasks(Pageable pageable) throws IOException {
        Page<Task> contactPage = taskService.getAllTasks(pageable);
        Page<TaskDTO> resultPage = contactPage.map(new Converter<Task, TaskDTO>() {
            @Override
            public TaskDTO convert(Task task) {
                return modelMapper.map(task,TaskDTO.class);
            }
        });
        return resultPage;
    }


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody TaskDTO findTaskByID(@PathVariable long id){
        Task task = taskService.getTaskById(id);
        TaskDTO taskDTO = modelMapper.map(task,TaskDTO.class);
        return taskDTO;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    void removeClient(@PathVariable long id){
        taskService.removeTask(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody String updateTask(@RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/task.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            TaskDTO taskDTO = objectMapper.readValue(string,TaskDTO.class);
            Task task = modelMapper.map(taskDTO,Task.class);
            taskService.updateTask(task);
        }
        System.out.println(report.toString());
        return report.toString();
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody String addTask(@RequestBody String string) throws ProcessingException, IOException {

        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        File file = new File(System.getProperty("user.home") + "/schemas/task.json");
        URI uri = file.toURI();

        JsonSchema jsonSchema = factory.getJsonSchema(uri.toString());

        ProcessingReport report;

        report = jsonSchema.validate(objectMapper.readTree(string));

        if (jsonSchema.validInstance(objectMapper.readTree(string))){
            TaskDTO taskDTO = objectMapper.readValue(string,TaskDTO.class);
            Task task = modelMapper.map(taskDTO,Task.class);
            taskService.addTask(task);
        }
        System.out.println(report.toString());
        return report.toString();
    }
}
