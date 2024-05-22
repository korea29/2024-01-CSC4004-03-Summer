package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 현제 시간표를 통해 듣는 강의 저장
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SubjectNow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sn_subjectnum", nullable = false, referencedColumnName = "subject_num")
    private Subject snSubjectnum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sn_studentid", nullable = false, referencedColumnName = "student_id")
    private User snStudentid;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "classroom", nullable = false, length = 100)
    private String classroom;

}