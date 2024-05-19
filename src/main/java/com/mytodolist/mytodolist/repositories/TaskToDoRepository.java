package com.mytodolist.mytodolist.repositories;

import com.mytodolist.mytodolist.models.ToDoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskToDoRepository extends JpaRepository<ToDoTask, Long> {
    List<ToDoTask> findAllByStatus (Boolean status);
}
