package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.Subject;
import com.dgu_csc.akomanager_back.service.Subjectservice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Subject")
public class Subjectcontroller {

    @Autowired
    private final Subjectservice subjectService;

    // POST :  [/Subject/add] 과목 추가 (학수번호 중복 확인)
    // TODO: 엑셀 컨트롤러와 연결 필요 (파싱된 문자열 판단해서 테이블에 삽입)
    @PostMapping("/add")
    public ResponseEntity<String> addSubject(@RequestBody Subject subject) {
        try {
            subjectService.saveSubject(subject);
            return ResponseEntity.ok("Subject added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // GET : [/Subject/{subjectNum}/get] url의 subjectNum으로 과목 정보 반환 (학수번호 일부만 검색 해도 나오게 구현)
    @GetMapping("/{subjectNum}/get")
    public ResponseEntity<Subject> getSubjectbySubjectNum(@PathVariable String subjectNum) {
        Optional<Subject> subject = subjectService.searchSubjectbysubjectNum(subjectNum);
        return subject.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/getAll")
    public List<Subject> List() {
        return subjectService.getAllsubject();
    }

}
