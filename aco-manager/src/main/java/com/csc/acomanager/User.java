package com.csc.acomanager;

import org.springframework.web.multipart.MultipartFile;

public class User {
    private final String id; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String university; // 대학교
    private String studentID; // 학번
    private String college; // 단과대학
    private String major; // 주전공
    private String doubleMajor; // 복수전공
    private String minor; // 부전공
    private MultipartFile profileImage; // 프로필 이미지

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getCollege() {
        return college;
    }
    public void setCollege(String college) {
        this.college = college;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getDoubleMajor() {
        return doubleMajor;
    }
    public void setDoubleMajor(String doubleMajor) {
        this.doubleMajor = doubleMajor;
    }
    public String getMinor() {
        return minor;
    }
    public void setMinor(String minor) {
        this.minor = minor;
    }
    public MultipartFile getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }
}
