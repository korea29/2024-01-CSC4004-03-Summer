package com.dgu_csc.akomanager_back.controller;

import com.dgu_csc.akomanager_back.dto.PasswordRequest;
import com.dgu_csc.akomanager_back.dto.UpdateUserRequest;
import com.dgu_csc.akomanager_back.jwt.JWTUtil;
import com.dgu_csc.akomanager_back.model.User;
import com.dgu_csc.akomanager_back.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/User")
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private OrderedFormContentFilter formContentFilter;
    @Autowired
    private JWTUtil jwtUtil;

//    private String extractToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }


    // POST :  [/User/add] 유저 추가 (학번 중복 확인)
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User users) {
        try {
            userService.saveUser(users);
            return ResponseEntity.ok("User added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    // POST : [/User/getAll] / 마스터 비밀번호를 body로 포함해서 요청하고, 권한 (role=11) 확인될 시, 정보 반환
    @PostMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(@RequestBody PasswordRequest request, HttpServletRequest request1) {
        String authorization= request1.getHeader("Authorization");
        String token = authorization.split(" ")[1];
        String studentId = jwtUtil.getUsername(token);
        Optional<User> masteruser = userService.findByStudentId(studentId);
        if( masteruser.isEmpty() ) {
            System.out.println("마스터 유저에 저장 안됨");
            return ResponseEntity.status(403).build();
        }
        else {
            try {
                if(masteruser.get().getRole().equals("11")){
                    List<User> Users = userService.getAllUsers(request.getPassword());
                    return ResponseEntity.ok(Users);
                }
                else {
                    System.out.println("role 값 다름");
                    return ResponseEntity.status(403).build();
                }
            } catch (SecurityException e) {
                return ResponseEntity.status(403).build();
            }
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
        Optional<User> user = userService.updateUser(studentId, request.getPassword(), request.getUpdatedUsers());
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 유저 정보 삭제
    @DeleteMapping("/{studentId}/delete")
    public ResponseEntity<Void> deleteUser(@PathVariable String studentId, @RequestBody PasswordRequest request) {
        boolean isDeleted = userService.deleteUser(studentId, request.getPassword());
        System.out.println(isDeleted);
        if (isDeleted) {
            System.out.println("delete complete!");
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
