package com.geekbrains.server.mappers;

import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { UserMapper.class, UserMapper.class, StatusMapper.class })
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "authorDto", target = "author")
    @Mapping(source = "executorDto", target = "executor")
    @Mapping(source = "statusDto", target = "status")
    Task toTask(TaskDto taskDto);

    @InheritInverseConfiguration
    TaskDto fromTask(Task task);

    List<Task> toTaskList(List<TaskDto> taskDtos);

    List<TaskDto> fromTaskList(List<Task> tasks);
}
