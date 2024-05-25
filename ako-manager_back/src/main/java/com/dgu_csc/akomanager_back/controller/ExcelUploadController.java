package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.Subject;
import com.dgu_csc.akomanager_back.model.SubjectFinished;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.SubjectFinishedService;
import com.dgu_csc.akomanager_back.service.SubjectService;
import com.dgu_csc.akomanager_back.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
@CrossOrigin(origins = "http://localhost:3000")
public class ExcelUploadController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectFinishedService subjectFinishedService;
    @Autowired
    private UserService userService;


    @PostMapping("/uploadS")
    public ResponseEntity<String> uploadExcelFileSubject(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        List<Subject> subjects = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                Subject subject = new Subject();
                for (Cell cell : row) {
                    // 엑셀 열을 기준으로 집어넣을 항목
                    switch (cell.getColumnIndex()) {
                        case 0:
                            break;
                        case 1:
                            // 개설 학년
                            subject.setEstablishedGrade(cell.toString());
                            break;
                        case 2:
                            // 개설 학기
                            subject.setOpenSemester(cell.toString());
                            break;
                        case 3:
                            // 교과 과정
                            subject.setCourseOfStudy(cell.toString());
                            break;
                        case 4:
                            break;
                        case 5:
                            // 이수 구분
                            subject.setClassificationOfCompletion(cell.toString());
                            break;
                        case 6:
                            // 학수 번호
                            subject.setSubjectNum(cell.toString());
                            break;
                        case 7:
                            // 과목명
                            subject.setSubjectName(cell.toString());
                            break;
                        case 8:
                            break;
                        case 9:
                            // 학점
                            subject.setGrade(Integer.parseInt(cell.toString()));
                            break;
                    }
                }
                subjects.add(subject);
            }
        }

        for (Subject subject : subjects) {
            try {
                subjectService.saveSubject(subject);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(409).body(e.getMessage());
            }
        }

        return ResponseEntity.ok("Subjects added successfully");
    }

}