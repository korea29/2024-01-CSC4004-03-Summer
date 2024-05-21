package com.dgu_csc.akomanager_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.security.auth.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

