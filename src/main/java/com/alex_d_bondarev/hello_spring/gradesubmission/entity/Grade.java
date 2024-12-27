package com.alex_d_bondarev.hello_spring.gradesubmission.entity;

public class Grade {

    private Long id;
    private String score;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
