package com.mytodolist.mytodolist;




import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.mytodolist.mytodolist.controllers.ShowTableFromDBController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.repositories.TaskToDoRepository;
import com.mytodolist.mytodolist.services.MainService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ShowTableFromDBControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
      private  TaskToDoRepository repository;
    @InjectMocks
    private  MainService mainService;

    @BeforeEach
    public void setup(){
        repository = Mockito.mock(TaskToDoRepository.class);
        mainService = new MainService(repository);
    }
    @Test
    void return_all() throws Exception {
       List<ToDoTask> tasks  = getTasks();
       Mockito.when(repository.findAll()).thenReturn(tasks);
       List<ToDoTask> result = mainService.readAll();

        Assertions.assertThat(result).isEqualTo(tasks);

    }
    @Test
    void add_task() throws Exception {
        ToDoTask task = new ToDoTask("testTaskDescription");
        task.setId(3L);
        mainService.create(task);
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(task));
        var newTask = mainService.findTask(3L);
        Assertions.assertThat(newTask.getDescription()).isEqualTo("testTaskDescription");
        Assertions.assertThat(newTask.getStatus()).isEqualTo(false);

    }

    @Test
    void add_task_empty() throws Exception {
        ToDoTask task = new ToDoTask();
        mainService.create(task);
        Assertions.assertThat(task.getStatus()).isEqualTo(false);

    }
    @Test
    void update_task() throws Exception {
        List<ToDoTask> tasks  = getTasks();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(tasks.get(0)));
        ToDoTask task1 = mainService.findTask(1L);
        task1.setDescription("newTest newTest");
        mainService.update(task1,1L);
        Assertions.assertThat(task1.getDescription()).isEqualTo("newTest newTest");
    }

    @Test
    void check_progress() throws Exception {
        TaskToDoRepository taskToDoRepository = mock(TaskToDoRepository.class); //Заглушка для репозитория
        MainService progressCalculator = new MainService(taskToDoRepository);
        when(taskToDoRepository.count()).thenReturn(5L); //Общее количество задач
        when(taskToDoRepository.findAllByStatus(true)).thenReturn(List.of(new ToDoTask(), new ToDoTask())); //Количество завершенных задач
        double result = progressCalculator.countProgress();

        assertEquals(40.0, result); //Ожидаемый результат: 40.0%
    }

    @Test
    public void check_showResults()
            throws Exception {

        mockMvc.perform(get("/showResults"))
                .andExpect(status().isOk());
                // .andExpect(content().string("resultsTemplate")); //Вставить ВСЕ содержимое resultTemplate? (Ожидает этого)

    }

    @Test
    public void testUpdateTask() throws Exception {
        long id = 26L;
        String description = "Test update description";
        ToDoTask task = new ToDoTask();
        task.setId(id);

        mockMvc.perform(post("/updateTask/{id}", id)
                        .param("description", description)
                        .param("status", String.valueOf(task.getStatus())))
                .andExpect(status().is3xxRedirection());

    }
    @Test
    public void testAddTask() throws Exception {
        String description = "Test add description";

        mockMvc.perform(post("/addNewTask")
                        .param("description", description))
                .andExpect(status().is3xxRedirection());

    }
    @Test
    public void testDeleteTask() throws Exception {
        long id = 9L;
          mockMvc.perform(post("/deleteTask/{id}",id))
                .andExpect(status().is3xxRedirection());

    }

    private List<ToDoTask> getTasks(){
        ToDoTask task1 = new ToDoTask("Task1 description");
        ToDoTask task2 = new ToDoTask("Task12 description");

        task1.setId(1L);
        task2.setId(2L);

        return List.of(task1,task2);
    }

}
