package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.Subject;

import java.util.List;
import java.util.Optional;

public interface Subjectservice {
    public void saveSubject(Subject subject);
    public List<Subject> getAllsubject();
    public Optional<Subject> searchSubjectbysubjectNum(String subjectNum);
    public Optional<Subject> searchSubjectbysubjectName(String subjectNum);
    public Optional<Subject> updateSubject(String subjectNum, Subject updateSubject);
    public boolean deleteSubject(String subjectNum, String password);
}
