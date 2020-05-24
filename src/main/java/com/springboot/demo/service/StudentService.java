package com.springboot.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.demo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentBySearch(Student student);
    int registerStudent(Student student);
    int updateStudents(JsonNode jsonObj);
}
