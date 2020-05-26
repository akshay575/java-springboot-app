package com.springboot.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.entity.Student;
import com.springboot.demo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentService service;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createOrUpdateStudentTest() throws Exception {
        Student student = new Student();
        student.setName("John");
        student.setCourse("EC");
        student.setAddress("NY");

        when(service.registerStudent(student)).thenReturn(1);

        String params = objectMapper.writeValueAsString(student);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/student/create")
                .content(params)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult response = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        JSONAssert.assertEquals(Collections.singletonMap("rowsUpdated", 1).toString(), response.getResponse().getContentAsString(), false);
    }
}
