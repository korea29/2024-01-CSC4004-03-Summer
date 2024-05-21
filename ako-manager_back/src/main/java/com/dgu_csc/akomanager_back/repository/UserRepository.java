package com.dgu_csc.akomanager_back.repository;

import com.dgu_csc.akomanager_back.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    Optional<user> findBystudentId(String studentId);
}

