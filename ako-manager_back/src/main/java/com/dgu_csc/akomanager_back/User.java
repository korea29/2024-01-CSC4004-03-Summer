package com.dgu_csc.akomanager_back;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Users", schema = "summer")
public class User {
    @Id
    @Column(name = "`학번`", nullable = false, length = 10)
    private String 학번;

    @Column(name = "`대학교`", nullable = false, length = 30)
    private String 대학교;

    @Column(name = "`이름`", nullable = false, length = 20)
    private String 이름;

    @Column(name = "`생년월일`", nullable = false)
    private LocalDate 생년월일;

    @Column(name = "`단과대학`", nullable = false, length = 40)
    private String 단과대학;

    @Column(name = "`주전공`", nullable = false, length = 100)
    private String 주전공;

    @Column(name = "`부전공`", length = 100)
    private String 부전공;

    @Column(name = "`아이디`", nullable = false, length = 10)
    private String 아이디;

    @Column(name = "`비밀번호`", nullable = false, length = 10)
    private String 비밀번호;

}