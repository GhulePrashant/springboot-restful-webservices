package com.prashantghule.springbootrestfulwebservices.mapper;

import com.prashantghule.springbootrestfulwebservices.dto.UserDto;
import com.prashantghule.springbootrestfulwebservices.entity.User;

public class UserMapper {

    // convert User JPA entity to UserDTo
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    // convert UserDto to User JPA Entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
