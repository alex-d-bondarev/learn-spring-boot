package com.alex_d_bondarev.hello_spring.gradesubmission.service;

import com.alex_d_bondarev.hello_spring.gradesubmission.entity.Grade;

import java.util.List;


public class GradeServiceImpl implements GradeService {
    
    @Override
    public Grade getGrade(Long studentId, Long courseId) {
        return null;
    }

    @Override
    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {
        return null;
    }

    @Override
    public Grade updateGrade(String score, Long studentId, Long courseId) {
        return null;
    }

    @Override
    public void deleteGrade(Long studentId, Long courseId) {
        
    }

    @Override
    public List<Grade> getStudentGrades(Long studentId) {
        return null;
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return null;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

}
