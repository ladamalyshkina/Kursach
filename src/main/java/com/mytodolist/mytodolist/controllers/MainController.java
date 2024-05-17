package com.mytodolist.mytodolist.controllers;

import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.repositories.TaskToDoRepository;
import com.mytodolist.mytodolist.services.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/home") // /-главная страница
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }
//    @GetMapping(value = "/clients")
//    public String read() {
//        final List<ToDoTask> clients = MainService.readAll();
//
//                return clients != null &&  !clients.isEmpty()
//                ? "not null"
//                : "null";

//        return clients != null &&  !clients.isEmpty()
//                ? new ResponseEntity<>(clients, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);


}
