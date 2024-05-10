package com.dgu_csc.akomanager_back;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
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
}
