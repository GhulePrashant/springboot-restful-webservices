package com.prashantghule.springbootrestfulwebservices.service;

import com.prashantghule.springbootrestfulwebservices.dto.UserDto;
import com.prashantghule.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);

    // overriding above methods to use DTO

    UserDto createUser(UserDto user);

    UserDto getUserDtoById(Long userId);

    List<UserDto> getAllUsersDto();

    UserDto updateUser(UserDto userDto);

}
