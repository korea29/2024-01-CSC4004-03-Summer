package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.Subject;

import java.util.List;

public interface Subjectservice {
    public Subject saveSubject(Subject subject);
    public List<Subject> getAllsubject();
}
