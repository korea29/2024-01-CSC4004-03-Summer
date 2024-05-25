package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.SubjectFinished;
import com.dgu_csc.akomanager_back.model.Users;
import com.dgu_csc.akomanager_back.repository.SubjectFinishedRepository;
import com.dgu_csc.akomanager_back.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectFinishedServiceImpl implements SubjectFinishedService{

    private final SubjectFinishedRepository subjectFinishedRepository;
    private static final String MASTER_PASSWORD = "SUMMER";
    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;


    // POST :  [/SubjectFinished/add] 들은 과목 추가 (학수번호 중복 확인)
    @Override
    @Transactional
    public void saveSubjectFinished(SubjectFinished subjectFinished) {
        if(!subjectFinishedRepository.findBySfSubjectnum(subjectFinished.getSfSubjectnum()).isEmpty()) {
            throw new IllegalArgumentException("SubjectFinished with subjectNum already exists");
        }
        subjectFinishedRepository.save(subjectFinished);
    }

    // GET : [/SubjectFinished/getAll] DB 전체 내용 출력
    @Override
    public List<SubjectFinished> getAllSubjectFinished(String masterPassword) {
        if(MASTER_PASSWORD.equals(masterPassword)) {
            return subjectFinishedRepository.findAll();
        } else {
            throw new SecurityException("마스터 비밀번호 오류");
        }
    }

    // POST : [/SubjectFinished/{studentId}/get] url의 studentId와 body의 password 정보로 해당 유저의 들은 과목 정보 출력
    public List<SubjectFinished> searchByStudentId(String studentId, String password) {
        Optional<Users> userOpt = userRepository.findByStudentId(studentId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Users users = userOpt.get();
        if (!users.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return subjectFinishedRepository.findBySfStudentid(users);
    }

    // TODO : 이수한 총 학점 수
    @Override
    public String getTotalScore() {
        return "";
    }

    // TODO : 이수한 총 전공 학점 수
    @Override
    public String getMajorTotalScore() {
        return "";
    }

    // TODO : 총 성적 평균

    // TODO : 총 전공 평균

}
