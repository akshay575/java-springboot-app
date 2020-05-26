package com.springboot.demo.service;

import com.springboot.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentService service;

    @Test
    public void getStudentBySearchTest() {
        Student s = new Student();
        s.setName("John");
        List<Student> list = new ArrayList<>();
        list.add(s);
        when(service.getStudentBySearch(s)).thenReturn(list);
        assertEquals(1, list.size());
    }
}
