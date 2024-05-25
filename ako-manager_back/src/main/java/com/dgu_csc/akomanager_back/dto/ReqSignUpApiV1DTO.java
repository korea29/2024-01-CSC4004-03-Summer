package com.dgu_csc.akomanager_back.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqSignUpApiV1DTO {
    @Id
    private String studentId;

    // 패스워드 규칙(영어 대/소문자, 숫자, 특수문자 모두 포함) 정규 표현식 설정
    private String password;

}