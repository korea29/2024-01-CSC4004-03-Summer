package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.dto.PasswordRequest;
import com.dgu_csc.akomanager_back.dto.UpdateUserRequest;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.Userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class Usercontroller {

    @Autowired
    private final Userservice userservice;

    // 이미 있는 유저인지 판단하고 유저 저장
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userservice.saveUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // 모든 유저 정보 반환
    @PostMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(@RequestBody PasswordRequest request) {
        try {
            List<User> Users = userservice.getAllUsers(request.getPassword());
            return ResponseEntity.ok(Users);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }


    // 유저 정보 검색후 반환 / POST : url에 아이디, body에 비밀번호 요청
    @PostMapping("/{studentId}/get")
    public ResponseEntity<User> getUserByStudentId(@PathVariable String studentId, @RequestBody PasswordRequest request) {
        Optional<User> user = userservice.search(studentId, request.getPassword());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 유저 정보 수정 (modify) / PUT : url에 아이디, body에 수정할 정보 입력
    @PutMapping("/{studentId}/update")
    public ResponseEntity<User> updateUser(@PathVariable String studentId, @RequestBody UpdateUserRequest request) {
        Optional<User> user = userservice.updateUser(studentId, request.getPassword(), request.getUpdatedUser());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 유저 정보 삭제
    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable String studentId, @RequestBody PasswordRequest request) {
        boolean isDeleted = userservice.deleteUser(studentId, request.getPassword());
        if (isDeleted) {
            System.out.println("delete complete!");
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
