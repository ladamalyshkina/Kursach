package com.mytodolist.mytodolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DatabaseCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkDBConnection() throws Exception {

        this.mockMvc.perform(get("/checkDBConnection"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Database connection successful")));
    }
}
