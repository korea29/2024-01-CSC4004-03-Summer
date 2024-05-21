package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.user;

import java.util.List;
import java.util.Optional;

public interface Userservice {
    public user saveUser(user user);
    public List<user> getAllUsers();
    public Optional<user> search(String key);
}
