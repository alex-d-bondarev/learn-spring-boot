package com.alex_d_bondarev.hello_spring.module1.intro;

import java.util.ArrayList;
import java.util.List;

import com.alex_d_bondarev.hello_spring.module1.Constants;
import com.alex_d_bondarev.hello_spring.module1.GradePojo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ValidateController {

    List<GradePojo> studentGrades = new ArrayList<>();

    @GetMapping("/validate/form")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new GradePojo() : studentGrades.get(index));
        return "intro/validate/form";
    }

    @PostMapping("/validate/handleSubmit")
    public String submitForm(@Valid @ModelAttribute("grade") GradePojo grade, BindingResult result, Model model) {
        if (result.hasErrors())
            return "intro/validate/form";

        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            studentGrades.add(grade);
        } else {
            studentGrades.set(index, grade);
        }
        return "redirect:/validate/grades";
    }

    @GetMapping("/validate/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "intro/validate/grades";
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

}
