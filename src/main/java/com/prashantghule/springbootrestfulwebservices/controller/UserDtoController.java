package com.prashantghule.springbootrestfulwebservices.controller;


import com.prashantghule.springbootrestfulwebservices.dto.UserDto;
import com.prashantghule.springbootrestfulwebservices.entity.User;
import com.prashantghule.springbootrestfulwebservices.exception.ErrorDetails;
import com.prashantghule.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.prashantghule.springbootrestfulwebservices.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;


@Tag(
        name = "CRUD Rest APIs for User Resource",
        description = "CRUD RestAPIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/dto/users")
public class UserDtoController {
    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    // getUserById REST API
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserDtoById(userId);

        return null!= user ? new ResponseEntity<>(user, HttpStatus.OK) : null;
    }

    // get all users REST API
    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get all users from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsersDto(), HttpStatus.OK);
    }

    // updateUser REST API
    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@Valid @RequestBody UserDto user){
        user.setId(userId);
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    //keeping same as UserController as no need to convert any JPA entity to DTO
    //deleteUser REST API
    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted : "+userId, HttpStatus.OK);
    }

    /*@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }*/

}
