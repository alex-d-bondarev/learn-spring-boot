package com.alex_d_bondarev.hello_spring;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
class HelloSpringApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testShowGradeForm() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/layers/form?id=123");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("layers/form"))
                .andExpect(model().attributeExists("grade"));
    }

    @Test
    public void testSuccessfulSubmission() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/layers/handleSubmit")
                .param("name", "Bob")
                .param("subject", "Maths")
                .param("score", "B+");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/layers/grades"));
    }

    @Test
    public void testUnsuccessfulSubmission() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/layers/handleSubmit")
                .param("name", "")
                .param("subject", "")
                .param("score", "R");

        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("layers/form"));
    }

    @Test
    public void testGetGrades() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/layers/grades");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("layers/grades"))
                .andExpect(model().attributeExists("grades"));
    }
}
