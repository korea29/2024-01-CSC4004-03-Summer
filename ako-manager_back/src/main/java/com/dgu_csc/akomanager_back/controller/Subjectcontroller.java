package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.subject;
import com.dgu_csc.akomanager_back.service.Subjectservice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class Subjectcontroller {

    @Autowired
    private final Subjectservice subjectservice;

    @PostMapping("/add")
    public void add(@RequestBody subject subject) {
        subjectservice.saveSubject(subject);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("[test did!!]");
        return "test";
    }

    @GetMapping("/getAll")
    public List<subject> List() {
        return subjectservice.getAllsubject();
    }

}
