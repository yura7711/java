package com.geekbrains.server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.geekbrains.gwt.common.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name="user_password")
    private String userPassword;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Task> tasksFromMe;

    @OneToMany(mappedBy = "executor", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Task> tasksForMe;

    @ManyToMany
    @JoinTable(name = "users_link_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
    }

    public List<Task> getTasksFromMe() {
        return tasksFromMe;
    }

    public List<Task> getTasksForMe() {
        return tasksForMe;
    }


}
