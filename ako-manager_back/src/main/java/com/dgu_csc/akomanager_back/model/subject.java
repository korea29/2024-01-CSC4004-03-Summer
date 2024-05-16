package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_num", nullable = false, length = 8)
    private String subjectNum;

    @Column(name = "established_grade", length = 8)
    private String establishedGrade;

    @Column(name = "open_semester", length = 8)
    private String openSemester;

    @Column(name = "` course_of_study`", length = 10)
    private String courseOfStudy;

    @Column(name = "classification_of_completion", length = 10)
    private String classificationOfCompletion;

    @Column(name = "subject_name", length = 100)
    private String subjectName;

    @Column(name = "grade", nullable = false)
    private Integer grade;

}