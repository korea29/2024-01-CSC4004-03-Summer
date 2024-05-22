package com.dgu_csc.akomanager_back.repository;

import com.dgu_csc.akomanager_back.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // 학수번호를 매개변수로 Subject 객체 반환
    Optional<Subject> findBysubjectNumContaining(String subjectNum);
    // 과목명을 매개변수로 Subject 객체 반환
    Optional<Subject> findBysubjectNameContaining(String subjectName);

}

