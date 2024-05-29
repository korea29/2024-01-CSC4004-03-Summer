package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.Major;
import com.dgu_csc.akomanager_back.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MajorServiceImpl implements MajorService{
    private final MajorRepository majorRepository;
    private static final String MASTER_PASSWORD = "SUMMER";

    // 관리자 전용 전공 정보 저장
    @Override
    public void saveMajor(Major major) {
        // 이미 전공 이름이 같은 항목이 존재 하면
        if(!majorRepository.findByMajorName(major.getMajorName()).isEmpty()) {
            // 같은 연도가 존재 하지 않으면 저장 안함
            if(majorRepository.findByYear(major.getYear()).isEmpty()){
                majorRepository.save(major);
            }
        }
    }


}
