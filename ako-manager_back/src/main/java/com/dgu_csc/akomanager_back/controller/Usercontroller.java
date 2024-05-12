package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.Userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
@CrossOrigin
public class Usercontroller {

    @Autowired
    private Userservice userservice;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userservice.saveUser(user);
        return "New User added!";
    }

    @GetMapping("/getAll")
    public List<User> List() {
        return userservice.getAllUsers();
    }

}
