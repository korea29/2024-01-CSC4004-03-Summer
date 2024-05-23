package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.dto.PasswordRequest;
import com.dgu_csc.akomanager_back.dto.UpdateUserRequest;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private final UserService userService;

    // POST :  [/User/add] 과목 추가 (학번 중복 확인)
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // POST : [/User/getAll] / 마스터 비밀번호를 body로 포함해서 요청하면 정보 반환
    @PostMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(@RequestBody PasswordRequest request) {
        try {
            List<User> Users = userService.getAllUsers(request.getPassword());
            return ResponseEntity.ok(Users);
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }


    // POST : [/User/{studentId}/get] url의 studentId와 body의 유저 개인의 password 정보로 유저 정보 반환
    @PostMapping("/{studentId}/get")
    public ResponseEntity<User> getUserByStudentId(@PathVariable String studentId, @RequestBody PasswordRequest request) {
            Optional<User> user = userService.searchUser(studentId, request.getPassword());
            return user.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 유저 정보 수정 (modify) / PUT : url에 아이디, body에 수정할 정보(/add 와 같이 ) 입력
    @PutMapping("/{studentId}/update")
    public ResponseEntity<User> updateUser(@PathVariable String studentId, @RequestBody UpdateUserRequest request) {
        Optional<User> user = userService.updateUser(studentId, request.getPassword(), request.getUpdatedUser());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 유저 정보 삭제
    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable String studentId, @RequestBody PasswordRequest request) {
        boolean isDeleted = userService.deleteUser(studentId, request.getPassword());
        if (isDeleted) {
            System.out.println("delete complete!");
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
