package com.alex_d_bondarev.hello_spring.layers.repository;

import com.alex_d_bondarev.hello_spring.GradePojo;

import java.util.ArrayList;
import java.util.List;

public class GradeRepository {
    private List<GradePojo> studentGrades = new ArrayList<>();

    public GradePojo getGrade(int index){
        return studentGrades.get(index);
    }

    public List<GradePojo> getGrades(){
        return studentGrades;
    }

    public void addGrade(GradePojo grade){
        studentGrades.add(grade);
    }

    public void updateGrade(int index, GradePojo grade){
        studentGrades.set(index, grade);
    }
}
