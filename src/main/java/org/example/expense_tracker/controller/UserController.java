package org.example.expense_tracker.controller;

import org.example.expense_tracker.dto.UserRegistrationDTO;
import org.example.expense_tracker.model.User;
import org.example.expense_tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    //Create User using DTO
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO dto){
        userService.registerUSer(dto);
        return ResponseEntity.ok("User Registered successfully");
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // Find User by userName
    @GetMapping("/filter/username")
    public User findByUserName(@RequestParam String username){
        return userService.getUserByEmail(username);
    }


}
