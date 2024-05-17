package com.mytodolist.mytodolist.controllers;

import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.repositories.TaskToDoRepository;
import com.mytodolist.mytodolist.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class TasksController {

    @Autowired
    private TaskToDoRepository repo;
    private final MainService mainService;

    public TasksController(MainService mainService) {
        this.mainService = mainService;
    }
    @RequestMapping(value = "/createTask") //@RequestBody ToDoTask client
    public void create() {
        System.out.println("CREATE4");
        ToDoTask task = new ToDoTask();
        System.out.println("CREATE1");
        task.setId(5L);
        task.setDescription("newnewnew");
        task.setDate();
        task.setStatus(false);
        System.out.println("CREATE3");
        mainService.create(task);

    }
    @RequestMapping(value = "/deleteTask") //@RequestBody ToDoTask client
    public void delete() {
        System.out.println("DEL");

        mainService.delete(3);

    }



//    @PostMapping ("/some/add")
//    public String add(Model model) {
//        var Kek = mainService.readAll();
//
//        if (!Kek.isEmpty()) {
//            ToDoTask firstElement = Kek.get(0);
//            model.addAttribute("tasks", Kek);
//            System.out.println("Id: " + firstElement.getId());
//            System.out.println("Description: " + firstElement.getDescription());
//            System.out.println("Date: " + firstElement.getDate());
//        } else {
//            System.out.println("Список пустой");
//        }
//
//        return "/home"; //Тут или не тут, а в простом, а не rest контроллере выполнить действия и перенавление указать
//    }

//    @RequestMapping(value = "/updateTask/{id}") //@RequestBody ToDoTask client
//    public void update(@RequestBody ToDoTask task,@PathVariable (value = "id") long id, Model model  ) {
//        System.out.println("UPDATE");
//        mainService.update(task,id);
//
//    }
@GetMapping (value = "/checkTask") //@RequestBody ToDoTask client
public String check(Model model) {

    var Kek = mainService.readAll();

    if (!Kek.isEmpty()) {
        ToDoTask firstElement = Kek.get(0);
        model.addAttribute("task", firstElement.getId());
        System.out.println("Id: " + firstElement.getId());
        System.out.println("Description: " + firstElement.getDescription());
        System.out.println("Date: " + firstElement.getDate());
    } else {
        System.out.println("Список пустой");
    }

return "task";
}
}
