package com.geekbrains.server.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @Column(name="role_id")
    private Long roleId;

    @Column(name="role_name")
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
