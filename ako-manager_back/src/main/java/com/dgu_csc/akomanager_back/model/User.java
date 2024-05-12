package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String 학번;

//    @Column(name = "`대학교`", nullable = false, length = 30)
    private String 대학교;

//    @Column(name = "`이름`", nullable = false, length = 20)
    private String 이름;

//    @Column(name = "`생년월일`", nullable = false)
    private LocalDate 생년월일;

//    @Column(name = "`단과대학`", nullable = false, length = 40)
    private String 단과대학;

//    @Column(name = "`주전공`", nullable = false, length = 100)
    private String 주전공;

//    @Column(name = "`부전공`", length = 100)
    private String 부전공;

//    @Column(name = "`아이디`", nullable = false, length = 10)
    private String 아이디;

//    @Column(name = "`비밀번호`", nullable = false, length = 10)
    private String 비밀번호;

//    public User(String 학번, String 대학교, String 이름, LocalDate 생년월일, String 단과대학) {
//        this.학번 = 학번;
//        this.대학교 = 대학교;
//        this.이름 = 이름;
//        this.생년월일 = 생년월일;
//        this.단과대학 = 단과대학;
//    }

//    public User() {
//
//    }


}