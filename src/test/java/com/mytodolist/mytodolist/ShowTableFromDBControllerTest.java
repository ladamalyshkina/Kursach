package com.mytodolist.mytodolist;




import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.mytodolist.mytodolist.controllers.ShowTableFromDBController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.services.MainService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    void chect_task_date() throws Exception {
        ToDoTask task = new ToDoTask();
      //  task.setDescription("testTaskDescription");
        mainService.create(task);
       // long currentTimeMillis = System.currentTime();

//        LocalDate taskDate = task.getDate().truncatedTo(ChronoUnit.MINUTES);
//        LocalDateTime expectedDate = d.truncatedTo(ChronoUnit.MINUTES);

//        Assertions.assertThat(taskDate).isEqualTo(expectedDate);
//
//        Assertions.assertThat(Objects.equals(task.getDate(), d)).isTrue();
       // Assertions.assertThat(task.getStatus() == false);

    }
//    @Test
//    void chect_task_status() throws Exception {
//        ToDoTask task = new ToDoTask();
//        //  task.setDescription("testTaskDescription");
//        mainService.create(task);
//        long currentTimeMillis = System.currentTimeMillis();
//
//        Date d =  new Date(currentTimeMillis);
//
//        Assertions.assertThat(Objects.equals(task.getDate(),d));
//
//    }
}
