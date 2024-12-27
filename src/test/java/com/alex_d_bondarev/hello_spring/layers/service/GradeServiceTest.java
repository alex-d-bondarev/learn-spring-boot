package com.alex_d_bondarev.hello_spring.layers.service;

import com.alex_d_bondarev.hello_spring.GradePojo;
import com.alex_d_bondarev.hello_spring.layers.repository.GradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.alex_d_bondarev.hello_spring.Constants.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepoMock;

    @InjectMocks
    private GradeService gradeService;

    @Test
    public void getGradesFromRepoTest() {
        when(gradeRepoMock.getGrades()).thenReturn(Arrays.asList(
                new GradePojo("Harry", "Potions", "C-"),
                new GradePojo("Hermione", "Arithmancy", "A+")
        ));

        List<GradePojo> grades = gradeService.getGrades();

        assertEquals("Harry", grades.getFirst().getName());
        assertEquals("Arithmancy", grades.getLast().getSubject());
    }

    @Test
    public void gradeIndexTest() {
        GradePojo harryGrade = new GradePojo("Harry", "Potions", "C-");
        when(gradeRepoMock.getGrades()).thenReturn(List.of(harryGrade));
        when(gradeRepoMock.getGrade(0)).thenReturn(harryGrade);

        List<GradePojo> grades = gradeService.getGrades();

        int valid = gradeService.getGradeIndex(harryGrade.getId());
        int notFound = gradeService.getGradeIndex("123");

        assertEquals(0, valid);
        assertEquals(NOT_FOUND, notFound);
    }

    @Test
    public void returnGradeByIdTest() {
        GradePojo harryGrade = new GradePojo("Harry", "Potions", "C-");
        when(gradeRepoMock.getGrades()).thenReturn(List.of(harryGrade));
        when(gradeRepoMock.getGrade(0)).thenReturn(harryGrade);

        String id = harryGrade.getId();
        GradePojo result = gradeService.getGradeById(id);

        assertEquals(harryGrade, result);
    }

    @Test
    public void addGradeTest() {
        GradePojo harryGrade = new GradePojo("Harry", "Potions", "C-");
        GradePojo hermioneGrade = new GradePojo("Hermione", "Arithmancy", "A+");
        when(gradeRepoMock.getGrades()).thenReturn(List.of(harryGrade));
        when(gradeRepoMock.getGrade(0)).thenReturn(harryGrade);

        gradeService.submitGrade(hermioneGrade);

        verify(gradeRepoMock, times(1)).addGrade(hermioneGrade);
    }

    @Test
    public void upgradeGradeTest() {
        GradePojo harryGrade = new GradePojo("Harry", "Potions", "C-");
        when(gradeRepoMock.getGrades()).thenReturn(List.of(harryGrade));
        when(gradeRepoMock.getGrade(0)).thenReturn(harryGrade);

        harryGrade.setScore("B-");
        gradeService.submitGrade(harryGrade);
        verify(gradeRepoMock, times(1)).updateGrade(0, harryGrade);
    }
}
