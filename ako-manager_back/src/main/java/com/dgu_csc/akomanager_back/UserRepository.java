package com.dgu_csc.akomanager_back;

import com.dgu_csc.akomanager_back.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
