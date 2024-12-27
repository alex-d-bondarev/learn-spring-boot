package com.alex_d_bondarev.hello_spring.gradesubmission.service;

import com.alex_d_bondarev.hello_spring.gradesubmission.entity.Student;

import java.util.List;


public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
}
