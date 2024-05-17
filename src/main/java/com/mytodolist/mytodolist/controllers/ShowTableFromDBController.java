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
        var Kek = mainService.readAll();

        if (!Kek.isEmpty()) {
            ToDoTask firstElement = Kek.get(0);
            model.addAttribute("tasks", Kek);
            System.out.println("Id: " + firstElement.getId());
            System.out.println("Description: " + firstElement.getDescription());
            System.out.println("Date: " + firstElement.getDate());
        } else {
            System.out.println("Список пустой");
        }

        return "resultsTemplate";
    }
    @PostMapping(value = "/updateTask/{id}") //@RequestBody ToDoTask client
    public String update(@RequestBody String description,@PathVariable (value = "id") long id  ) {
        System.out.println("UPDATE");
        ToDoTask task = repo.findById(id).orElseThrow();
        // Optional<ToDoTask> task = repo.findById(id);
        task.setDescription(description);
        repo.save(task);
        return "redirect:/showResults";
    }

    @PostMapping ("/some/add")
    public String add(@RequestBody String description) {
        Boolean status = false;
        ToDoTask task = new ToDoTask(description);
        repo.save(task);
        return "redirect:/showResults";
    }

    @RequestMapping(value = "/deleteTask/{id}") //@RequestBody ToDoTask client
    public String delete(@PathVariable (value = "id") long id  ) {
        System.out.println("DELETE");

       repo.deleteById(id);
        return "redirect:/showResults";


    }


}
