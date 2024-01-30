package com.prashantghule.springbootrestfulwebservices.mapper;

import com.prashantghule.springbootrestfulwebservices.dto.UserDto;
import com.prashantghule.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
