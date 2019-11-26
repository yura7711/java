package com.geekbrains.training.lesson11.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Task> tasksFromMe;

    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    private List<Task> tasksForMe;

    public User() {
    }

    public List<Task> getTasksFromMe() {
        return tasksFromMe;
    }

    public List<Task> getTasksForMe() {
        return tasksForMe;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
