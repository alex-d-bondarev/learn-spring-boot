package com.alex_d_bondarev.hello_spring.module1.web;

import java.util.Arrays;
import java.util.List;

import com.alex_d_bondarev.hello_spring.module1.GradePojo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*") //Not to worry. These annotations will be covered in Module 2.
public class WebGradeController {

    List<GradePojo> list = Arrays.asList(
            new GradePojo("Harry", "Potions", "C-"),
            new GradePojo("Hermione", "Arithmancy", "A+"),
            new GradePojo("Neville", "Charms", "A-")
    );

    @GetMapping("/web/grades")
    public ResponseEntity<List<GradePojo>> getGrades() {
        return new ResponseEntity<List<GradePojo>>(list, HttpStatus.OK);
    }
}
