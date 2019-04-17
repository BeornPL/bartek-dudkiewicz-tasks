package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask/{id}")
    public TaskDto getTask(@PathVariable("id") Long taskId) {
        return taskMapper.mapToTaskDto(service.getTaskById(taskId));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(Long taskId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(TaskDto taskDto) {

    }
}

//    @RequestMapping(method = RequestMethod.GET, value = "getTasks/{id}")
//    public TaskDto getTasks(@PathVariable("id") Long taskId) {
//        return new TaskDto(1L, "test task", "test_content");
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask/{id}")
//    public void deleteTask(@PathVariable("id") Long taskId) {
//
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "updateTask/{taskDto}")
//    public TaskDto updateTask(@PathVariable("taskDto") TaskDto taskDto) {
//        return new TaskDto(1L, "Edited test title", "Test content");
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "createTask/{taskDto}")
//    public void createTask(@PathVariable("taskDto") TaskDto taskDto) {
//
//    }
