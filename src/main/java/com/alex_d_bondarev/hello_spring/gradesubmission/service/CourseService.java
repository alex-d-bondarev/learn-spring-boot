package com.alex_d_bondarev.hello_spring.gradesubmission.service;

import com.alex_d_bondarev.hello_spring.gradesubmission.entity.Course;

import java.util.List;


public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
}
