package com.geekbrains.server.mappers;

import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatusMapper {
    StatusMapper MAPPER = Mappers.getMapper(StatusMapper.class);

    Task.Status toStatus(TaskDto.StatusDto statusDto);

    @InheritInverseConfiguration
    TaskDto.StatusDto fromStatus(Task.Status status);

    List<Task.Status> toStatusList(TaskDto.StatusDto[] statusDto);

    List<TaskDto.StatusDto> fromStatusList(Task.Status[] statuses);
}
