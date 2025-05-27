package org.example.expense_tracker.controller;

import org.example.expense_tracker.model.User;
import org.example.expense_tracker.service.UserService;
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    // Find User by userName
    @GetMapping("/filter/username")
    public List<User> findByUserName(@RequestParam String username){
        return userService.getUserByEmail(username);
    }


}
