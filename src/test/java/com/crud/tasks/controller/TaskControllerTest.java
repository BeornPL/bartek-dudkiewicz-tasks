package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DbService service;

    @MockBean
    TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "1", "Test");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("1")))
                .andExpect(jsonPath("$[0].content", is("Test")));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Long taskId = Long.valueOf(1);
        TaskDto taskDto = new TaskDto(taskId, "1", "Test");

        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("1")))
                .andExpect(jsonPath("$.content", is("Test")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        Long taskId = Long.valueOf(1);
        TaskDto taskDto = new TaskDto(taskId, "1", "Test");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .contentType(MediaType.APPLICATION_JSON)
                .param("taskId", "1"))
                .andExpect(status().is(200));
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        Long taskId = Long.valueOf(1);
        TaskDto taskDto = new TaskDto(taskId, "1", "Test");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("1")))
                .andExpect(jsonPath("$.content", is("Test")));
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        Long taskId = Long.valueOf(1);
        TaskDto taskDto = new TaskDto(taskId, "1", "Test");
        Task task = new Task(taskId, "1", "Test");
        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("1")))
                .andExpect(jsonPath("$.content", is("Test")));
    }
}