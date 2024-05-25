//package com.dgu_csc.akomanager_back.auth;
//
//import com.dgu_csc.akomanager_back.dto.ReqSignUpApiV1DTO;
//import com.dgu_csc.akomanager_back.model.Users;
//import com.dgu_csc.akomanager_back.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class AuthServiceApiV1 {
//
//    private final UserRepository userRepository;
//
//    // 추후 구현, 사용 예정
//    private final JwtProvider jwtProvider;
//    private final PasswordEncoder passwordEncoder;
//
//    @Transactional
//    public HttpEntity<?> signUp(ReqSignUpApiV1DTO reqSignUpApiV1DTO) {
//
//        // ReqDTO 기반으로 유저 정보 생성
//        Users users = Users.builder()
//                .email(reqSignUpApiV1DTO.getStudentId())
//                .password(passwordEncoder.encode(reqSignUpApiV1DTO.getPassword()))
//                .role("ROLE_MEMBER")
//                .build();
//
//        // 유저 정보 저장
//        userRepository.save(users);
//
//        // 회원가입 성공
//        return new ResponseEntity<>(
//                ResDTO.builder()
//                        .code(0)
//                        .message("회원가입 성공")
//                        .build(),
//                HttpStatus.OK);
//    }
//}