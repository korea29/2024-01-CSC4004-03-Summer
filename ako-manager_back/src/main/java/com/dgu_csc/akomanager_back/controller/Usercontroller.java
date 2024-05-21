package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.Userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class Usercontroller {

    @Autowired
    private final Userservice userservice;

    @PostMapping("/add")
    public void add(@RequestBody User user) {
        userservice.saveUser(user);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("[test did!!]");
        return "test";
    }

    @GetMapping("/getAll")
    public List<User> List() {
        return userservice.getAllUsers();
    }

}
