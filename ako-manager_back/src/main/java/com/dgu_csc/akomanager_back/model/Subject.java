package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Subject", schema = "summer")
public class Subject {
    @Id
    @Column(name = "`학수번호`", nullable = false, length = 8)
    private String 학수번호;

    @Column(name = "`개설학년`", nullable = false, length = 8)
    private String 개설학년;

    @Column(name = "`개설학기`", nullable = false, length = 8)
    private String 개설학기;

    @Column(name = "`교과과정`", nullable = false, length = 10)
    private String 교과과정;

    @Column(name = "`이수구분`", nullable = false, length = 10)
    private String 이수구분;

    @Column(name = "`교과목명`", nullable = false, length = 100)
    private String 교과목명;

    @Column(name = "`학점`", nullable = false)
    private Integer 학점;

}