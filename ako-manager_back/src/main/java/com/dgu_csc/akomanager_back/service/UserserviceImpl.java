package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.user;
import com.dgu_csc.akomanager_back.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserserviceImpl implements Userservice{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public user saveUser(user user) {
        return userRepository.save(user);
    }

    @Override
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<user> search(String key) {
        return userRepository.findBystudentId(key);
    }

}
