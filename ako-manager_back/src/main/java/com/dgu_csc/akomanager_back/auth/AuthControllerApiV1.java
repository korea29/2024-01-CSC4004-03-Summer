//package com.dgu_csc.akomanager_back.auth;
//
//
//import com.dgu_csc.akomanager_back.dto.ReqSignUpApiV1DTO;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.apache.coyote.BadRequestException;
//import org.springframework.http.HttpEntity;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/auth")
//public class AuthControllerApiV1 {
//
//    private final AuthServiceApiV1 authServiceApiV1;
//
//    // 회원가입
//    @PostMapping("/sign-up")
//    public HttpEntity<?> signUp(@RequestBody @Valid ReqSignUpApiV1DTO reqSignUpApiV1DTO, Errors error) {
//        // Validation 중 에러 발생 시, BadRequestException 발생
//        if (error.hasErrors()) {
//            throw new BadRequestException(error.getAllErrors().get(0).getDefaultMessage());
//        }
//        return authServiceApiV1.signUp(reqSignUpApiV1DTO);
//    }
//}