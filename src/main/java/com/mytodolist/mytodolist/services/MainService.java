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
        taskToDoRepository.save(task);
    }


    public  List<ToDoTask> readAll() {
        return taskToDoRepository.findAll();
    }
    public List<ToDoTask> readAllSortedByID() {
        return taskToDoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public void update(ToDoTask task, long id) {
        if (taskToDoRepository.existsById(id)) {
            task.setId(id);
            taskToDoRepository.save(task);

        }

    }
    public ToDoTask findTask(long id)
    {
        return taskToDoRepository.findById(id).orElseThrow();
    }
    public double countProgress()
    {
        long tasksCount = taskToDoRepository.count();
        if (tasksCount !=0) {

            var completedTasks = taskToDoRepository.findAllByStatus(true);
            double percentCompleted = ((double) completedTasks.size() / tasksCount) * 100;
            return Math.round(percentCompleted * 100.0) / 100.0;
        }
        else {
            return 0.0;
        }

    }
    public void deleteAll() {
        taskToDoRepository.deleteAll();
    }

    public void delete(long id) {
        if (taskToDoRepository.existsById(id)) {
            taskToDoRepository.deleteById(id);
        }
    }
}