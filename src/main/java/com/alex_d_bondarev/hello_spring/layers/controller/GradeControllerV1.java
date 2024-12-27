package com.alex_d_bondarev.hello_spring.layers.controller;

import com.alex_d_bondarev.hello_spring.GradePojo;
import com.alex_d_bondarev.hello_spring.layers.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller
public class GradeControllerV1 {

    private final GradeService gradeService;

    @Autowired
    public GradeControllerV1(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/layers/form")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        model.addAttribute(
                "grade", gradeService.getGradeById(id));
        return "layers/form";
    }

    @PostMapping("/layers/handleSubmit")
    public String submitForm(@Valid @ModelAttribute("grade") GradePojo grade, BindingResult result, Model model) {
        if (result.hasErrors())
            return "layers/form";

        gradeService.submitGrade(grade);
        return "redirect:/layers/grades";
    }

    @GetMapping("/layers/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getGrades());
        return "layers/grades";
    }
}
