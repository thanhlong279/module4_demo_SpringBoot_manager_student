package com.example.demo_spring_boot.services.impl;

import com.example.demo_spring_boot.models.Student;
import com.example.demo_spring_boot.repositories.IStudentRepository;
import com.example.demo_spring_boot.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Page<Student> findAllByName(String name, Pageable pageable) {
        return studentRepository.findAllByNameContaining("%"+ name+"%", pageable);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findStudentByIdIs(id);
    }

    public void update(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
        } else {
            throw new IllegalArgumentException("Student not found");
        }
    }

}
