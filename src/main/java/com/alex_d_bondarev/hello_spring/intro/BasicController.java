package com.alex_d_bondarev.hello_spring.intro;

import com.alex_d_bondarev.hello_spring.Constants;
import com.alex_d_bondarev.hello_spring.GradePojo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BasicController {

    List<GradePojo> studentGrades = new ArrayList<>();

    @GetMapping("/basic/grade")
    public String getGrade(Model model) {
        GradePojo grade = new GradePojo("Harry", "Potions", "C-");
        model.addAttribute("grade", grade);
        return "intro/basic/grade";
    }

    @GetMapping("/basic/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "intro/basic/grades";
    }

    @GetMapping("/basic/form")
    public String gradeForm(Model model, @RequestParam(required = false) String id) {
        Integer index = getGradeIndex(id);
        model.addAttribute(
                "grade",
                index == Constants.NOT_FOUND ? new GradePojo() : studentGrades.get(index)
        );
        model.addAttribute("types", Constants.SCORE_TYPE);
        return "intro/basic/form";
    }

    @GetMapping(value = "/basic/conditional")
    public String getConditional(Model model) {
        model.addAttribute("sales", 30);
        model.addAttribute("product", "chair");
        return "intro/basic/conditionals";
    }

    @GetMapping(value = "/basic/utility")
    public String getUtility(Model model) {
        model.addAttribute("menu", "We sell chocolate rice cakes bubble tea");
        return "intro/basic/utility";
    }

    @PostMapping(value = "/basic/handleSubmit")
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
        return "redirect:/intro/basic/grades";
    }

    public Integer getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id))
                return i;
        }
        return Constants.NOT_FOUND;
    }
}
