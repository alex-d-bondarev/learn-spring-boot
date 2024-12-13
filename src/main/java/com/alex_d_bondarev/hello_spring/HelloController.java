package com.alex_d_bondarev.hello_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    List<GradePojo> studentGrades = new ArrayList<>();

    @GetMapping("/grade")
    public String getGrade(Model model) {
        GradePojo grade = new GradePojo("Harry", "Potions", "C-");
        model.addAttribute("grade", grade);
        return "grade";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    @GetMapping("/grade/form")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
        Integer index = getGradeIndex(id);
        model.addAttribute(
                "grade",
                index == Constants.NOT_FOUND ? new GradePojo() : studentGrades.get(index)
        );
        model.addAttribute("scores", Constants.SCORE);
        return "form";
    }

    @GetMapping(value = "/conditional")
    public String getConditional(Model model) {
        model.addAttribute("sales", 30);
        model.addAttribute("product", "chair");
        return "conditionals";
    }

    @GetMapping(value = "/utility")
    public String getUtility(Model model) {
        model.addAttribute("menu", "We sell chocolate rice cakes bubble tea");
        return "utility";
    }

    @PostMapping(value = "/handleSubmit")
    public String submitGrade(GradePojo grade, RedirectAttributes redirectAttributes) {
        Integer index = getGradeIndex(grade.getId());
        String status = Constants.SUCCESS_STATUS;
        try {
            if (index == Constants.NOT_FOUND) {
                studentGrades.add(grade);
            } else {
                studentGrades.set(index, grade);
            }
        } catch (Exception ex) {
            status = Constants.FAILED_STATUS;
        }

        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }
}
