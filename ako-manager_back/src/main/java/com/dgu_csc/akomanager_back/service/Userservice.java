package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.user;

import java.util.List;

public interface Userservice {
    public user saveUser(user user);
    public List<user> getAllUsers();
}
