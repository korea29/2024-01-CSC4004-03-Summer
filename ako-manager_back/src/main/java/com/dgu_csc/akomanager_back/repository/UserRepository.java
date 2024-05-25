package com.dgu_csc.akomanager_back.repository;

import com.dgu_csc.akomanager_back.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // 학번을 매개변수로 객체 반환
    Optional<Users> findByStudentId(String studentId);

}

