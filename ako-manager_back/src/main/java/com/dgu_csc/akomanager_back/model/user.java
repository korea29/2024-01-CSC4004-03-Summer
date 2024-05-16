    package com.dgu_csc.akomanager_back.model;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

    @NoArgsConstructor
    @Getter
    @Setter
    @Entity
    public class user {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "student_id", nullable = false, length = 10)
        private String studentId;

        @Column(name = "`university`", nullable = false, length = 30)
        private String university;

        @Column(name = "`name`", nullable = false, length = 20)
        private String name;

        @Column(name = "`date_of_birth`")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate date_of_birth;

        @Column(name = "`college`", nullable = false, length = 40)
        private String college;

        @Column(name = "`major`", nullable = false, length = 100)
        private String major;

        @Column(name = "`minor`", nullable = true , length = 100)
        private String minor;

        @Column(name = "`username`", nullable = false, length = 10)
        private String username;

        @Column(name = "`password`", nullable = false, length = 10)
        private String password;

    }
