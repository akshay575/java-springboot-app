package com.springboot.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.demo.entity.Student;
import com.springboot.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository repository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Student> getStudentBySearch(Student student) {
        if(student.getRegistration() == null) {
            Example<Student> request = Example.of(student);
            Page<Student> result = repository.findAll(request, PageRequest.of(0, 20));
            return result.getContent();
        } else {
            Date date = new Date(student.getRegistration().getTime());
            String regAfter = date.toString() + " 00:00:00";
            String regBefore = date.toString() + " 23:59:59";
            return repository.searchStudentByQuery(student, Timestamp.valueOf(regAfter), Timestamp.valueOf(regBefore), PageRequest.of(0, 20));
        }
    }

    @Override
    public int registerStudent(Student student) {
        student.setRegistration(new Timestamp(System.currentTimeMillis()));
        Student s = repository.save(student);
        if(s.getSid() > 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public int updateStudents(JsonNode jsonObj) {
        List<Integer> idList = objectMapper.convertValue(jsonObj.get("idList"), new TypeReference<List<Integer>>() {});
        int rowsUpdated = repository.updateStudents(idList, jsonObj.get("course").textValue());
        return rowsUpdated;
    }
}
