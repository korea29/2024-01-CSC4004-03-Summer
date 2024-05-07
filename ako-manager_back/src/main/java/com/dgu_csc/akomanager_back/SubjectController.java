package com.dgu_csc.akomanager_back;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    private final List<Subject> subjects = new ArrayList<Subject>();

    public SubjectController() {
    }

    @GetMapping
    Iterable<Subject> getSubjects() {
        return subjects;
    }

    @GetMapping("/{courseNumber}")
    Optional<Subject> getSubject(@PathVariable String courseNumber) {
        for (Subject subject : subjects) {
            if (courseNumber.equals(subject.getCourseNumber())) {
                return Optional.of(subject);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Subject postSubject(@RequestBody Subject subject) {
        subjects.add(subject);
        return subject;
    }

    @PutMapping("/{courseNumber}")
    ResponseEntity<Subject> putSubject(@PathVariable String courseNumber, @RequestBody Subject subject) {
        int subjectIndex = -1;
        for (Subject s : subjects) {
            if (courseNumber.equals(s.getCourseNumber())) {
                subjectIndex = subjects.indexOf(s);
                subjects.set(subjectIndex, subject);
            }
        }

        return (subjectIndex == -1) ?
                new ResponseEntity<>(postSubject(subject), HttpStatus.CREATED) :
                new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/{courseNumber}")
    void deleteSubject(@PathVariable String courseNumber) {
        subjects.removeIf(subject -> subject.getCourseNumber().equals(courseNumber));
    }

}
