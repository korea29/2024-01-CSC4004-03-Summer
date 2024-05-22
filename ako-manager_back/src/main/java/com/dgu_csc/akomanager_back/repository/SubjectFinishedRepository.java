package com.dgu_csc.akomanager_back.repository;

import com.dgu_csc.akomanager_back.model.Subject;
import com.dgu_csc.akomanager_back.model.SubjectFinished;
import com.dgu_csc.akomanager_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFinishedRepository extends JpaRepository<SubjectFinished, Long> {
    List<SubjectFinished> findBySfStudentid(User user);
    List<SubjectFinished> findBySfSubjectnum(Subject subject);
}
