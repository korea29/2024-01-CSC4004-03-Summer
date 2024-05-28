package com.dgu_csc.akomanager_back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity

public class ChatBot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 질문자의 이름
    @Column(name = "name", nullable = false, length = 10)
    private String name;

    // 질문내
    @Column(name = "UserInput")
    private String userInput;
}

