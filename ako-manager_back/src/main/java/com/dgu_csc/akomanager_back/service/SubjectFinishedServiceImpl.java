package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.SubjectFinished;
import com.dgu_csc.akomanager_back.repository.SubjectFinishedRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectFinishedServiceImpl implements SubjectFinishedService{

    private final SubjectFinishedRepository subjectFinishedRepository;



    // POST :  [/SubjectFinished/add] 들은 과목 추가 (학수번호 중복 확인)
    @Override
    @Transactional
    public void saveSubjectFinished(SubjectFinished subjectFinished) {
        if(!subjectFinishedRepository.findBySfSubjectnum(subjectFinished.getSfSubjectnum()).isEmpty()) {
            throw new IllegalArgumentException("SubjectFinished with subjectNum already exists");
        }
        subjectFinishedRepository.save(subjectFinished);
    }


    @Override
    public List<SubjectFinished> getAllSubjectFinished() {
        return List.of();
    }

    @Override
    public List<SubjectFinished> searchByStudentId(String studentId) {
        return List.of();
    }

    @Override
    public String getTotalScore() {
        return "";
    }

    @Override
    public String getMajorTotalScore() {
        return "";
    }
}
