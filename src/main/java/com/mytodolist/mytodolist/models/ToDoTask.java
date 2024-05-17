package com.mytodolist.mytodolist.models;

import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name = "tasks")
public class ToDoTask {
    @Id
    @Column(name = "id_task")
    @SequenceGenerator(name = "tasksIdSeq", sequenceName = "tasks_id_seq", allocationSize = 1) //allocationSize -?
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasksIdSeq")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column (name = "task_date")
    private Date taskDate;

    @Column(name = "status")
    private Boolean status = false;
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public String setDescription2(String description)
    {
        this.description = description;
        return description;
    }
    public Date getDate() {return taskDate;}

    public void setDate() {
        // Получаем текущую дату и время
        long currentTimeMillis = System.currentTimeMillis();

        // Создаем объект java.sql.Date на основе текущего времени
        this.taskDate = new Date(currentTimeMillis);
    }
    public Date setDefaultDate() {
        // Получаем текущую дату и время
        long currentTimeMillis = System.currentTimeMillis();

        // Создаем объект java.sql.Date на основе текущего времени
        return new Date(currentTimeMillis);
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }
    public Boolean getStatus()
    {
        return status;
    }

    public ToDoTask(){}
    public ToDoTask(String description)
    {
        this.description = description;
        this.taskDate = setDefaultDate();
        this.status = status;


    }

}
