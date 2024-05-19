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
        var taskList = mainService.readAllSortedByDate();

        if (!taskList.isEmpty()) {
          //  ToDoTask firstElement = taskList.get(0);
            model.addAttribute("tasks", taskList);
//            System.out.println("Id: " + firstElement.getId());
//            System.out.println("Description: " + firstElement.getDescription());
//            System.out.println("Date: " + firstElement.getDate());
           var progress =  mainService.countProgress();
            model.addAttribute("progress", progress);
            System.out.println("PROGRESS: " + progress);
        } else {
            System.out.println("Список пустой");
        }

        return "resultsTemplate";
    }


    @PostMapping(value = "/updateTask/{id}") //@RequestBody ToDoTask client
    public String update(@PathVariable (value = "id") long id, @RequestParam String description, @RequestParam (value = "status", required = false) String status  ) {
        System.out.println("UPDATE");
        ToDoTask task = repo.findById(id).orElseThrow();
        if (status!=null) {
            task.setStatus(true);
            System.out.println("UPDATE");
            System.out.println("STATUS" + status);
        }
        else {
            task.setStatus(false);
            System.out.println("STATUS IS NULL");
        }
        task.setDescription(description);
     //   System.out.println("STATUS"+ status);
     //   task.setStatus(status);
        repo.save(task);
        return "redirect:/showResults";
    }


//    @PostMapping(value = "/updateTaskStatus/{id}") //@RequestBody ToDoTask client
//    public String updateTaskStatus(@PathVariable (value = "id") long id, @RequestParam (value = "status", required = false) String status ) {
//        ToDoTask task = repo.findById(id).orElseThrow();
//        if (status!=null) {
//            task.setStatus(true);
//            System.out.println("UPDATE");
//            System.out.println("STATUS" + status);
//        }
//        else {
//            task.setStatus(false);
//            System.out.println("STATUS IS NULL");
//        }
//
////
////        task.setStatus(status);
////          System.out.println("STATUS"+ status);
//        //   task.setStatus(status);
//        repo.save(task);
//        return "redirect:/showResults";
//    }

    @PostMapping ("/addNewTask")
    public String add(@RequestParam String description) {
        ToDoTask task = new ToDoTask(description);
        repo.save(task);
        return "redirect:/showResults";
    }

    @RequestMapping(value = "/deleteTask/{id}") //@RequestBody ToDoTask task
    public String delete(@PathVariable (value = "id") long id  ) {
        System.out.println("DELETE");
        repo.deleteById(id);
        return "redirect:/showResults";


    }

    @RequestMapping(value = "/deleteAllTasks") //@RequestBody ToDoTask task
    public String deleteAll( ) {
        //System.out.println("DELETE");
        repo.deleteAll();
        return "redirect:/showResults";


    }


}
