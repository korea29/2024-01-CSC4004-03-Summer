package com.dgu_csc.akomanager_back.repository;

import com.dgu_csc.akomanager_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

