package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class UserserviceImpl implements Userservice{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
