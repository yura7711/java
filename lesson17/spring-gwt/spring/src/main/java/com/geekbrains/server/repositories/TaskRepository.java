package com.geekbrains.server.repositories;

import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import com.geekbrains.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    @Query("SELECT new com.geekbrains.gwt.common.TaskDto(t.id, t.name, t.author.userName, t.executor.userName, t.description) FROM Task t order by t.id")
    List<TaskDto> findAllTaskDto();
}
