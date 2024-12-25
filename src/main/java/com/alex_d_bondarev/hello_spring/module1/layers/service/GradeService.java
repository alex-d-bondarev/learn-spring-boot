package com.alex_d_bondarev.hello_spring.module1.layers.service;

import com.alex_d_bondarev.hello_spring.module1.Constants;
import com.alex_d_bondarev.hello_spring.module1.GradePojo;
import com.alex_d_bondarev.hello_spring.module1.layers.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    public GradeService(GradeRepository gradeRepo){
        this.gradeRepo = gradeRepo;
    }

    GradeRepository gradeRepo;

    public GradePojo getGrade(int index){
        return gradeRepo.getGrade(index);
    }

    public List<GradePojo> getGrades(){
        return gradeRepo.getGrades();
    }

    public void addGrade(GradePojo grade){
        gradeRepo.addGrade(grade);
    }

    public void updateGrade(int index, GradePojo grade){
        gradeRepo.updateGrade(index, grade);
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < this.getGrades().size(); i++) {
            if (this.getGrade(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public GradePojo getGradeById(String id){
        int index = this.getGradeIndex(id);
        return index == Constants.NOT_FOUND ? new GradePojo() : this.getGrade(index);
    }

    public void submitGrade(GradePojo grade){
        int index = this.getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            this.addGrade(grade);
        } else {
            this.updateGrade(index, grade);
        }
    }
}
