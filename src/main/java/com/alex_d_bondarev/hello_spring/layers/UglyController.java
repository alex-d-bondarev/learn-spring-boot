package com.alex_d_bondarev.hello_spring.layers;

import com.alex_d_bondarev.hello_spring.Constants;
import com.alex_d_bondarev.hello_spring.GradePojo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UglyController {

    List<GradePojo> studentGrades = new ArrayList<>();

    @GetMapping("/layers/form")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new GradePojo() : studentGrades.get(index));
        return "layers/form";
    }

    @PostMapping("/layers/handleSubmit")
    public String submitForm(@Valid @ModelAttribute("grade") GradePojo grade, BindingResult result, Model model) {
        if (result.hasErrors())
            return "layers/form";

        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            studentGrades.add(grade);
        } else {
            studentGrades.set(index, grade);
        }
        return "redirect:/layers/grades";
    }

    @GetMapping("/layers/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "layers/grades";
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

}
