package com.springboot.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.springboot.demo.entity.Student;
import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Integer>> createOrUpdateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(Collections.singletonMap("rowsUpdated", service.registerStudent(student)));
    }

    @PostMapping("/search")
    public ResponseEntity<List<Student>> getStudentsBySearch(@RequestBody Student student) {
        return ResponseEntity.ok(service.getStudentBySearch(student));
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Integer>> updateStudents(@RequestBody JsonNode jsonObj) {
        return ResponseEntity.ok(Collections.singletonMap("rowsUpdated", service.updateStudents(jsonObj)));
    }
}
