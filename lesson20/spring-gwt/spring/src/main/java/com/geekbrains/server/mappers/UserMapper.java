package com.geekbrains.server.mappers;

import com.geekbrains.gwt.common.UserDto;
import com.geekbrains.server.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    //@Mapping(target = "roles", ignore = true)
    //@Mapping(target = "authors", ignore = true)
    //@Mapping(target = "executors", ignore = true)
    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto fromUser(User user);

    List<User> toUserList(List<UserDto> userDtos);

    List<UserDto> fromUserList(List<User> users);
}
