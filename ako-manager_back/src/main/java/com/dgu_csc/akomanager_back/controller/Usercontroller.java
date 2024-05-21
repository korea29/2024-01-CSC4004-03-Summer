package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.user;
import com.dgu_csc.akomanager_back.service.Userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class Usercontroller {

    @Autowired
    private final Userservice userservice;

    @PostMapping("/add")
    public void add(@RequestBody user user) {
        userservice.saveUser(user);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("[test did!!]");
        return "test";
    }

    @GetMapping("/getAll")
    public List<user> List() {
        return userservice.getAllUsers();
    }

    // 검색 기능
    @GetMapping("/{studentId}")
    public ResponseEntity<user> getInfo(@PathVariable String studentId){
        Optional<user> user = userservice.search(studentId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
