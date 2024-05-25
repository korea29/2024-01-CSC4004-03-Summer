package com.dgu_csc.akomanager_back.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TokenDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long token_pk_id;

    @Column
    private String grantType;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private String member_id;
}