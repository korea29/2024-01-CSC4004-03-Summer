package com.dgu_csc.akomanager_back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final DataService dataService;

    @Autowired
    public Controller(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("/insert")
    public void insertData(@RequestBody MyData data) {
        dataService.insertData(data);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteData(@PathVariable Long id) {
        dataService.deleteData(id);
    }


}
