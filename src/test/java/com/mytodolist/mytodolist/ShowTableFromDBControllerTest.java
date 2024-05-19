package com.mytodolist.mytodolist;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.mytodolist.mytodolist.controllers.ShowTableFromDBController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.repositories.TaskToDoRepository;
import com.mytodolist.mytodolist.services.MainService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ShowTableFromDBControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MainService mainService;

    @Test
    void add_task() throws Exception {
        ToDoTask task = new ToDoTask("testTaskDescription");
      // task.setDescription("testTaskDescription");
        mainService.create(task);
//        CreateRedirectResponse resp = makeShorterRequest(String.format("""
//                {"longUrl":"%s", "vipKey": "%s"}}
//                """, longUrl, vipKey));



        Assertions.assertThat(task.getDescription()).isEqualTo("testTaskDescription");
//        Assertions.assertThat(resp.getShortUrl()).endsWith(vipKey);
//
//        this.mockMvc.perform(get(resp.getShortUrl()))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(header().string("Location", longUrl));
    }

    @Test
    void add_task_empty() throws Exception {
        ToDoTask task = new ToDoTask();

        mainService.create(task);
        Assertions.assertThat(task.getStatus()).isEqualTo(false);
        //Assertions.assertThat(task.getStatus()).isEqualTo(false);

    }
    @Test
    void check_task_date() throws Exception {
        ToDoTask task = new ToDoTask();
      //  task.setDescription("testTaskDescription");
     //   mainService.create(task);
       // long currentTimeMillis = System.currentTime();

//        LocalDate taskDate = task.getDate().truncatedTo(ChronoUnit.MINUTES);
//        LocalDateTime expectedDate = d.truncatedTo(ChronoUnit.MINUTES);

//        Assertions.assertThat(taskDate).isEqualTo(expectedDate);
//
//        Assertions.assertThat(Objects.equals(task.getDate(), d)).isTrue();
       // Assertions.assertThat(task.getStatus() == false);

    }
    @Test
    void check_progress() throws Exception {
        // Создаем заглушку (mock) для репозитория
        TaskToDoRepository taskToDoRepository = mock(TaskToDoRepository.class);

        // Создаем экземпляр класса, содержащего метод countProgress()
        MainService progressCalculator = new MainService(taskToDoRepository);

        // Подготовка данных для теста
        when(taskToDoRepository.count()).thenReturn(5L); // Общее количество задач
        when(taskToDoRepository.findAllByStatus(true)).thenReturn(List.of(new ToDoTask(), new ToDoTask())); // Количество завершенных задач

        // Вызываем тестируемый метод
        double result = progressCalculator.countProgress();

        // Проверяем, что результат соответствует ожидаемому значению
        assertEquals(40.0, result, 0.01); // Ожидаемый результат: 40.0%
    }

//    @Test
//    public void testUpdateTask() throws Exception {
//        // Подготовка данных для теста
//        long id = 65L;
//        String description = "Test description";
//        Boolean status = false;
//
//        // Вызываем метод update с заданными параметрами
//        mockMvc.perform(post("/updateTask/{id}", id)
//                        .param("description", description)
//                        .param("status", String.valueOf(status)))
//                .andExpect(status().is3xxRedirection());
//
//        // Проверяем, что метод mainService.update был вызван с правильными аргументами
//        Mockito.verify(mainService).update(Mockito.any(ToDoTask.class), Mockito.eq(id));
//    }

//    @Test
//    public void testShowResults() {
//        // Создаем список задач для заглушки
//        List<ToDoTask> taskList = new ArrayList<>();
//        taskList.add(new ToDoTask( "Task 1"));
//        taskList.add(new ToDoTask( "Task 2"));
//
//        // Устанавливаем поведение заглушки для методов сервиса
//        when(mainService.readAllSortedByID()).thenReturn(taskList);
//        when(mainService.countProgress()).thenReturn(50.0);
//
//        // Вызываем метод контроллера
//        ShowTableFromDBController.showResults(model);
//
//        // Проверяем, что модель была заполнена правильно
//        verify(model).addAttribute("tasks", taskList);
//        verify(model).addAttribute("progress", 50);
//
//        // Проверяем вывод в консоль в зависимости от списка задач
//        if (taskList.isEmpty()) {
//            verify(System.out).println("Список пустой");
//        } else {
//            verify(System.out).println("PROGRESS: 50");
//        }
//
//        // Проверяем вызов методов сервиса
//        verify(mainService).readAllSortedByID();
//        verify(mainService).countProgress();
//
//        // Проверяем, что возвращается правильное имя шаблона
//        assertEquals("resultsTemplate", yourController.showResults(model));
//    }
}
