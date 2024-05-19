package com.mytodolist.mytodolist.services;

import com.mytodolist.mytodolist.models.ToDoTask;
import com.mytodolist.mytodolist.repositories.TaskToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private  final  TaskToDoRepository taskToDoRepository;

    @Autowired
    public MainService(TaskToDoRepository taskToDoRepository) {
        this.taskToDoRepository = taskToDoRepository;
    }


    public void create(ToDoTask task) {
        System.out.println("CREATE");
        taskToDoRepository.save(task);
    }


    public  List<ToDoTask> readAll() {
        System.out.println("READ");
        return taskToDoRepository.findAll();
    }
    public List<ToDoTask> readAllSortedByDate() {
        System.out.println("READ SORTED BY DATE");
        return taskToDoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
//    @Override
//    public ToDoTask read(long id) {
//        return taskToDoRepository.getOne(id);
//    }


    public boolean update(ToDoTask task, long id) {
        if (taskToDoRepository.existsById(id)) {
            task.setId(id);
            taskToDoRepository.save(task);
            return true;
        }

        return false;
    }

    public void deleteAll() {
      taskToDoRepository.deleteAll();
    }

    public boolean delete(long id) {
        if (taskToDoRepository.existsById(id)) {
            taskToDoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
