package com.mytodolist.mytodolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseCheckController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/checkdbconnection")
    public String checkDBConnection() {
        try {
            jdbcTemplate.execute("SELECT * from tasks");
            return "Database connection successful";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }

}
