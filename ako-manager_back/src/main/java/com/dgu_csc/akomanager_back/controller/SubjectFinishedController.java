package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.dto.PasswordRequest;
import com.dgu_csc.akomanager_back.model.SubjectFinished;
import com.dgu_csc.akomanager_back.service.SubjectFinishedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/SubjectFinished")
public class SubjectFinishedController {

    @Autowired
    private final SubjectFinishedService subjectFinishedService;

    // POST : [/SubjectFinished/{studentId}/get] url의 studentId와 body의 password 정보로 해당 유저의 들은 과목 정보 출력
    @PostMapping("/{studentId}/get")
    public ResponseEntity<List<SubjectFinished>> getSubjectsByStudentId(@PathVariable String studentId, @RequestBody PasswordRequest request) {
        try {
            List<SubjectFinished> subjects = subjectFinishedService.searchByStudentId(studentId, request.getPassword());
            return ResponseEntity.ok(subjects);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/{studentId}/totalGrage")
    public int getTotalGrade(@PathVariable String studentId) {
        return subjectFinishedService.getTotalScore(studentId);
    }

}
