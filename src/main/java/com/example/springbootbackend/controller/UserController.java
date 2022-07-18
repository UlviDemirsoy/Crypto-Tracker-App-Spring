package com.example.springbootbackend.controller;
import java.util.List;
import java.util.Date;
import com.example.springbootbackend.model.Alert;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.service.AlertService;
import com.example.springbootbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){

        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }



}
