package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.model.Subject;
import com.dgu_csc.akomanager_back.service.SubjectService;
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
public class ExcelUploadController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
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

    public static double gradeToDouble(String input)
    {
        return switch (input) {
            case "A+" -> 4.5;
            case "A" -> 4.0;
            case "B+" -> 3.5;
            case "B" -> 3.0;
            case "C+" -> 2.5;
            case "C" -> 2.0;
            case "D+" -> 1.5;
            case "D" -> 1.0;
            default -> 0.0;
        };
    }

    // 요일 정보, 강의실 정보를 받아 파싱하는 함수
    public static List<String> parseSchedule(String timeString, String locationString) {
        List<String> results = new ArrayList<>();

        // 입력값이 null이거나 공백인지 확인
        if (timeString == null || timeString.trim().isEmpty() || locationString == null || locationString.trim().isEmpty()) {
            results.add("사이버 강의"); // 공백 문자열 반환
            return results;
        }

        String[] timeEntries = timeString.split(",");
        String[] locationEntries = locationString.split(",");

        if (timeEntries.length != locationEntries.length) {
            throw new IllegalArgumentException("Time entries and location entries do not match in length.");
        }

        for (int i = 0; i < timeEntries.length; i++) {
            String day = timeEntries[i].substring(0, 1);  // 요일 추출
            String location = locationEntries[i];

            int startIndex = location.indexOf("(");
            int endIndex = location.lastIndexOf(")");

            if (startIndex == -1 || endIndex == -1 || startIndex >= endIndex) {
                throw new IllegalArgumentException("Invalid location format: " + location);
            }

            String locationName = location.substring(startIndex + 1, endIndex);
            String[] locationParts = locationName.split(" ");

            if (locationParts.length < 2) {
                throw new IllegalArgumentException("Invalid location format: " + location);
            }

            String buildingWithInfo = locationParts[0];
            String room = locationParts[1];
            String floor;

            // 층 번호 추출 로직 수정
            if (Character.isAlphabetic(room.charAt(0))) {
                floor = room.substring(1, 2); // 두 번째 숫자를 층 번호로 사용
            } else {
                floor = room.substring(0, 1); // 첫 번째 숫자를 층 번호로 사용
            }

            // 건물 이름에서 '(' 이후의 부분 제거
            String building = buildingWithInfo.split("\\(")[0];

            results.add("(" + day + ", 동국대학교 " + building + " " + floor + "층) ");
        }

        return results;
    }
}


