package com.mytodolist.mytodolist.controllers;

import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.services.MainService;
import com.mytodolist.mytodolist.repositories.TaskToDoRepository;
import com.mytodolist.mytodolist.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.services.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Controller
public class ShowTableFromDBController {
    @Autowired
    private TaskToDoRepository repo;
    private final MainService mainService;

    public ShowTableFromDBController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/showResults")
    public String showResults(Model model) {
        var taskList = mainService.readAllSortedByID();
        if (!taskList.isEmpty()) {
            model.addAttribute("tasks", taskList);
            var progress =  mainService.countProgress();
            model.addAttribute("progress", progress);
        } else {
            System.out.println("Список пустой");
        }
        return "resultsTemplate";
    }


    @PostMapping(value = "/updateTask/{id}")
    public String update(@PathVariable (value = "id") long id, @RequestParam String description, @RequestParam (value = "status", required = false) String status  ) {
        ToDoTask task = mainService.findTask(id);
        if (status!=null) {
            task.setStatus(true);
        }
        else {
            task.setStatus(false);
        }
        task.setDescription(description);
        mainService.update(task,id);
        return "redirect:/showResults";
    }


    @PostMapping ("/addNewTask")
    public String add(@RequestParam String description) {
        ToDoTask task = new ToDoTask(description);
        mainService.create(task);
        return "redirect:/showResults";
    }

    @RequestMapping(value = "/deleteTask/{id}")
    public String delete(@PathVariable (value = "id") long id  ) {
        mainService.delete(id);
        return "redirect:/showResults";


    }

    @RequestMapping(value = "/deleteAllTasks")
    public String deleteAll( ) {
        mainService.deleteAll();
        return "redirect:/showResults";

    }


}