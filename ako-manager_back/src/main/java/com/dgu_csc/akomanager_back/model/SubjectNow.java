package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "Subject_now", schema = "summer")
public class SubjectNow {
    @Id
    @Column(name = "SNNOW", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "`학수번호`", nullable = false)
    private Subject 학수번호;

    @ColumnDefault("0")
    @Column(name = "`재수강`", nullable = false)
    private Integer 재수강;

    @Column(name = "`강의실`", length = 100)
    private String 강의실;

}