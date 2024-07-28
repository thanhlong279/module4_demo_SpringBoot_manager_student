package com.example.demo_spring_boot.services;


import com.example.demo_spring_boot.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> findAll();

    void save(Student student);

    Page<Student> findAllByName(String name, Pageable pageable);

    Student findById(Long id);

    void update(Student student);

    void deleteStudent(Student student1);
}
