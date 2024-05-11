package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "Subject_finshed", schema = "summer")
public class SubjectFinshed {
    @Id
    @Column(name = "SFNUM", nullable = false)
    private Integer id;

    @Column(name = "`성적`", nullable = false, length = 4)
    private String 성적;

    @ColumnDefault("0")
    @Column(name = "`재수강`", nullable = false)
    private Integer 재수강;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "`학수번호`", nullable = false)
    private Subject 학수번호;

}