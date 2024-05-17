package com.dgu_csc.akomanager_back.repository;

import com.dgu_csc.akomanager_back.model.subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<subject, Long> {
}

