package com.prashantghule.springbootrestfulwebservices.controller;

import com.prashantghule.springbootrestfulwebservices.entity.User;
import com.prashantghule.springbootrestfulwebservices.exception.ErrorDetails;
import com.prashantghule.springbootrestfulwebservices.exception.ResourceNotFoundException;
import com.prashantghule.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // create User REST API
    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // getUserById REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);

        return null!= user ? new ResponseEntity<>(user, HttpStatus.OK) : null;
    }

    // get all users REST API
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // updateUser REST API
    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user){
        user.setId(userId);
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    //deleteUser REST API
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted : "+userId, HttpStatus.OK);
    }

/*    @ExceptionHandler(ResourceNotFoundException.class)
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
