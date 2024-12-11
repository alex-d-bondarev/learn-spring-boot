package com.alex_d_bondarev.hello_spring;

import java.util.UUID;

public class GradePojo {
    private String name;
    private String subject;
    private String score;
    private String id;

    public GradePojo(String name, String subject, String score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

    public GradePojo() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
