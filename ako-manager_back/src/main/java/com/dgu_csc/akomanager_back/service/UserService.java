package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void saveUser(Users users);
    public List<Users> getAllUsers(String massterPassword);
    public Optional<Users> searchUser(String studentId, String password);
    public Optional<Users> updateUser(String studentId, String password, Users updatedUsers);
    public boolean deleteUser(String studentId, String password);
    Optional<Users> findByStudentId(String string);
}
