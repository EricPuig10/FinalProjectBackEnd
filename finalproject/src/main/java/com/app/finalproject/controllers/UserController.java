package com.app.finalproject.controllers;

import com.app.finalproject.models.User;
import com.app.finalproject.services.userS.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    User getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
