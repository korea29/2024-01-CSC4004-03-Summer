package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.User;
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

    // "/getAll" 을 위한 master 비밀번호
    private static final String MASTER_PASSWORD = "SUMMER";

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // get : 전체 유저 반환 (master 전용)
    public List<User> getAllUsers(String masterPassword) {
        if (MASTER_PASSWORD.equals(masterPassword)) {
            return userRepository.findAll();
        } else {
            throw new SecurityException("마스터 비밀번호 오류");
        }
    }

    // get : studentId와 해당 password로 유저 정보 반환
    @Override
    public Optional<User> search(String studentId, String password) {
        return userRepository.findBystudentId(studentId).filter(user -> user.getPassword().equals(password));
    }

    // put : studentId와 해당 password로 유저 정보 수정
    @Override
    public Optional<User> updateUser(String studentId, String password, User updatedUser) {
        return userRepository.findBystudentId(studentId)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    user.setUniversity(updatedUser.getUniversity());
                    user.setName(updatedUser.getName());
                    user.setDate_of_birth(updatedUser.getDate_of_birth());
                    user.setCollege(updatedUser.getCollege());
                    user.setMajor(updatedUser.getMajor());
                    user.setMinor(updatedUser.getMinor());
                    user.setUsername(updatedUser.getUsername());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                });
    }

    // 비밀번호 판단
    public boolean deleteUser(String studentId, String password) {
        return userRepository.findBystudentId(studentId)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    userRepository.delete(user);
                    return true;
                }).orElse(false);
    }
}
