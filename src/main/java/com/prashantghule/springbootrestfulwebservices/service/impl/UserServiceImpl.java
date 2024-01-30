package com.prashantghule.springbootrestfulwebservices.service.impl;

import com.prashantghule.springbootrestfulwebservices.dto.UserDto;
import com.prashantghule.springbootrestfulwebservices.entity.User;
import com.prashantghule.springbootrestfulwebservices.exception.EmailAlreadyExistsException;
import com.prashantghule.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.prashantghule.springbootrestfulwebservices.mapper.AutoUserMapper;
import com.prashantghule.springbootrestfulwebservices.mapper.UserMapper;
import com.prashantghule.springbootrestfulwebservices.repository.UserRepository;
import com.prashantghule.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public User createUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists : "+ user.getEmail());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser= userRepository.findById(userId);
        return !optionalUser.isEmpty() ? optionalUser.get() : null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
//        User existingUser = userRepository.findById(user.getId()).isPresent() ? userRepository.findById(user.getId()).get() : null;
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId())
        );
         if (existingUser != null) {
             existingUser.setFirstName(user.getFirstName());
             existingUser.setLastName(user.getLastName());
             existingUser.setEmail(user.getEmail());

             return userRepository.save(existingUser);
         }
         return null;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
    }


    // implementing DTO methods
    @Override
    public UserDto createUser(UserDto userDto) {
        //convert userDto to user JPA entity because userRepository needs User instance

        // using customized UserMapper class
//        User user = UserMapper.mapToUser(userDto);

//        checking if email already exists or not
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email already exists : "+ userDto.getEmail());
        }

        //using ModelMapper library
        User user = modelMapper.map(userDto, User.class);

//        using mapstruct to convert user jpa entity to userDto and vice versa
//        User user1 = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // converting User JPA entity to userDto
//        UserDto savedDtoUser = UserMapper.mapToUserDto(savedUser);

        UserDto savedDtoUser = modelMapper.map(savedUser, UserDto.class);

        return savedDtoUser;
    }

    @Override
    public UserDto getUserDtoById(Long userId) {
//        Optional<User> optionalUser= userRepository.findById(userId);
//        return optionalUser.map(UserMapper::mapToUserDto).orElse(null);
//        User user = optionalUser.orElse(null);

//        using mapstruct
//        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());

//        Customized ResourceNotFoundException Handling
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsersDto() {
        List<User> allUsers = userRepository.findAll();

//        return allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        return allUsers.stream().map((user -> modelMapper.map(user, UserDto.class))).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
//        User existingUser = userRepository.findById(userDto.getId()).isPresent() ? userRepository.findById(userDto.getId()).get() : null;
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        if (existingUser != null) {
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setEmail(userDto.getEmail());

//            return UserMapper.mapToUserDto(userRepository.save(existingUser));
            User updatedUser = userRepository.save(existingUser);
            return modelMapper.map(updatedUser, UserDto.class);
        }
        return null;
    }
}
