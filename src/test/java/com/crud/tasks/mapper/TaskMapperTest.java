package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(Long.valueOf(1), mappedTask.getId());
        assertEquals("test_title", mappedTask.getTitle());
        assertEquals("test_content", mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test_title", "test_content");
        //When
        TaskDto mappedTask = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(Long.valueOf(1), mappedTask.getId());
        assertEquals("test_title", mappedTask.getTitle());
        assertEquals("test_content", mappedTask.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test_title", "test_content"));
        //When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1, mappedList.size());
        assertEquals(Long.valueOf(1), mappedList.get(0).getId());
        assertEquals("test_title", mappedList.get(0).getTitle());
        assertEquals("test_content", mappedList.get(0).getContent());
    }
}
