package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.Subject;
import com.dgu_csc.akomanager_back.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SubjectserviceImpl implements Subjectservice{

    private final SubjectRepository subjectRepository;
    private static final String MASTER_PASSWORD = "SUMMER";

    // POST :  [/Subject/add] 과목 추가 (학수번호 중복 확인)
    @Override
    @Transactional
    public void saveSubject(Subject subject) {
        if(subjectRepository.findBysubjectNumContaining(subject.getSubjectNum()).isPresent()) {
            throw new IllegalArgumentException("동일한 학수번호가 존재하는 과목이 존재합니다.");
        }
        subjectRepository.save(subject);
    }

    // GET : [/Subject/getAll] / user와 다르게 별도의 마스터 비밀 번호 필요 없다.
    @Override
    public List<Subject> getAllsubject() {
        return subjectRepository.findAll();
    }

    // 해당 문자열이 포함된 학수번호 검색시 관련 항목 뜨게
    // 학수번호와 과목명으로 검색 해서 subject 반환 하는 기능
    // 검색은 포함된 으로 변경 (Containing)

    // GET : [/Subject/{subjectNum}/get] url의 subjectNum으로 과목 정보 반환 (학수번호 일부만 검색 해도 나오게 구현)
    @Override
    public Optional<Subject> searchSubjectbysubjectNum(String subjectNum) {
        return subjectRepository.findBysubjectNumContaining(subjectNum);
    }

    // GET : [/Subject/{subjectName}/get] url의 subjectName으로 과목 정보 반환 (과목명 일부만 검색 해도 나오게 구현)
    @Override
    public Optional<Subject> searchSubjectbysubjectName(String subjectName) {
        return subjectRepository.findBysubjectNameContaining(subjectName);
    }

    // PUT : [/Subject/{subjectNum}/update] url의 studentId와 body의 password 정보로 유저 정보 수정
    @Override
    public Optional<Subject> updateSubject(String subjectNum, Subject updateSubject) {
        return subjectRepository.findBysubjectNameContaining(subjectNum)
                .filter(subject -> subject.getSubjectNum().equals(subjectNum))
                .map(subject -> {
                    subject.setSubjectNum(updateSubject.getSubjectNum());
                    subject.setSubjectName(updateSubject.getSubjectName());
                    subject.setEstablishedGrade(updateSubject.getEstablishedGrade());
                    subject.setOpenSemester(updateSubject.getOpenSemester());
                    subject.setGrade(updateSubject.getGrade());
                    subject.setCourseOfStudy(updateSubject.getCourseOfStudy());
                    subject.setClassificationOfCompletion(updateSubject.getClassificationOfCompletion());
                    return subjectRepository.save(subject);
                });
    }

    // DELETE : [/User/{subjectNum}/delete] url의 studentId와 body의 마스터 비밀번호로 과목 정보 삭제
    @Override
    public boolean deleteSubject(String subjectNum, String password) {
        if(password.equals(MASTER_PASSWORD))
            return true;
        else
            return false;
    }
}
