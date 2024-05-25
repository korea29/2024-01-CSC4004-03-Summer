package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.Users;
import com.dgu_csc.akomanager_back.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // POST : [/User/getAll] 을 위한 master 비밀번호
    private static final String MASTER_PASSWORD = "SUMMER";

    // POST : [/User/add]
    @Override
    @Transactional
    public void saveUser(Users users) {
        if (userRepository.findByStudentId(users.getStudentId()).isPresent()) {
            throw new IllegalArgumentException("동일한 학번이 존재하는 유저가 존재합니다.");
        }
        userRepository.save(users);
    }

    // POST : [/User/getAll] => 전체 유저 반환 (master 전용)
    public List<Users> getAllUsers(String masterPassword) {
        if (MASTER_PASSWORD.equals(masterPassword)) {
            return userRepository.findAll();
        } else {
            throw new SecurityException("마스터 비밀번호 오류");
        }
    }

    // POST : [/User/{studentId}/get] url의 studentId와 body의 password 정보로 유저 정보 반환
    @Override
    public Optional<Users> searchUser(String studentId, String password) {
        return userRepository.findByStudentId(studentId).filter(user -> user.getPassword().equals(password));
    }

    // PUT : [/User/{studentId}/update] url의 studentId와 body의 password 정보로 유저 정보 수정
    @Override
    public Optional<Users> updateUser(String studentId, String password, Users updatedUsers) {
        return userRepository.findByStudentId(studentId)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> {
                    user.setUniversity(updatedUsers.getUniversity());
                    user.setName(updatedUsers.getName());
                    user.setDate_of_birth(updatedUsers.getDate_of_birth());
                    user.setCollege(updatedUsers.getCollege());
                    user.setMajor(updatedUsers.getMajor());
                    user.setMinor(updatedUsers.getMinor());
                    user.setUsername(updatedUsers.getUsername());
                    user.setPassword(updatedUsers.getPassword());
                    return userRepository.save(user);
                });
    }

    // DELETE : [/User/{studentId}/delete] url의 studentId와 body의 유저 개인 비밀번호나 마스터 비밀번호로 유저 정보 삭제
    public boolean deleteUser(String studentId, String password) {

        if(password.equals(MASTER_PASSWORD))
            return true;
        else
            return userRepository.findByStudentId(studentId)
                    .filter(user -> user.getPassword().equals(password))
                    .map(user -> {
                        userRepository.delete(user);
                        return true;
                    }).orElse(false);

    }

    @Override
    public Optional<Users> findByStudentId(String studentid) {
        return userRepository.findByStudentId(studentid);
    }
}
