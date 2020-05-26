package com.springboot.demo.repository;

import com.springboot.demo.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.course = :course WHERE s.sid IN :idList")
    int updateStudents(List<Integer> idList, String course);

    // SpEL method parameter expressions :#
    @Query("SELECT s from Student s where (:#{#student.name} IS NULL OR s.name = :#{#student.name}) " +
            "AND (:regAfter IS NULL OR (s.registration >= :regAfter AND s.registration <= :regBefore))")
    List<Student> searchStudentByQuery(Student student, Timestamp regAfter, Timestamp regBefore, Pageable pageable);
}
