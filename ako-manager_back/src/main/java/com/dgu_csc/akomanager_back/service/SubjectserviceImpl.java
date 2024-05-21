package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SubjectserviceImpl implements Subjectservice{

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<subject> getAllsubject() {
        return subjectRepository.findAll();
    }
}
