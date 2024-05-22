package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class SubjectFinished {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Subject의 subjectNum을 외래키로 가지는 항목
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sf_subjectnum", nullable = false, referencedColumnName = "subject_num")
    private Subject sfSubjectnum;

    // 학점(성적)
    @Column(name = "score", nullable = false, length = 4)
    private String score;

    // User의
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sf_studentid", nullable = false, referencedColumnName = "student_id")
    private User sfStudentid;

    @ColumnDefault("0")
    @Column(name = "re_class", nullable = false)
    private Boolean reClass = false;

}