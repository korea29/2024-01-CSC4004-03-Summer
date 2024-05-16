package com.dgu_csc.akomanager_back.service;

import com.dgu_csc.akomanager_back.model.subject;

import java.util.List;

public interface Subjectservice {
    public subject saveSubject(subject subject);
    public List<subject> getAllsubject();
}
