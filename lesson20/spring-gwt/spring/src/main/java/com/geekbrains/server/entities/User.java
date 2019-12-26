package com.geekbrains.server.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.geekbrains.gwt.common.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jws.soap.SOAPBinding;
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

    @ManyToMany
    @JoinTable(name = "users_link_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
