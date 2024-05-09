package com.dgu_csc.akomanager_back;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {
    // User 객체 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID; // 학번 [PK]
    private String university; // 대학교
    private String name; // 이름
    private String date; // 생년월일
    private String college; // 단과대학
    private String major; // 주전공
    private String minor; // 부전공
    private String id; // 아이디
    private String password; // 비밀번호

    // 객체 초기화
    public User(Long studentID, String university, String name, String date , String college, String major, String minor, String id, String password) {
        this.studentID = studentID;
        this.university = university;
        this.name = name;
        this.date = date;
        this.college = college;
        this.major = major;
        this.minor = minor;
        this.id = id;
        this.password = password;
    }

    // 학번 설정 [PK]
    public Long getStudentID() {
        return this.studentID;
    }
    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    // 대학교 설정
    public String getUniversity() {
        return this.university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }

    // 이름 설정
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // 생년월일 설정
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    // 단과대학 설정
    public String getCollege() {
        return this.college;
    }
    public void setCollege(String college) {
        this.college = college;
    }

    // 주전공 설정
    public String getMajor() {
        return this.major;
    }
    public void setMajor(String major) {
        this.major = major;
    }

    // 부전공 설정
    public String getMinor() {
        return this.minor;
    }
    public void setMinor(String minor) {
        this.minor = minor;
    }

    // 아이디 설정
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // 비밀번호 설정
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
