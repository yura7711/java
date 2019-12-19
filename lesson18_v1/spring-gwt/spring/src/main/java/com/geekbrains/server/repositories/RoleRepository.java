package com.geekbrains.server.repositories;


import com.geekbrains.server.entities.Role;
import com.geekbrains.server.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
	Role findOneByName(String name);
}
