package com.alex_d_bondarev.hello_spring.module1.layers.repository;

import com.alex_d_bondarev.hello_spring.module1.GradePojo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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
