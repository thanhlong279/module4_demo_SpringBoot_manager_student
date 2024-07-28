package com.example.demo_spring_boot.controllers;

import com.example.demo_spring_boot.models.Student;
import com.example.demo_spring_boot.services.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student student1 = studentService.findById(id);
        if (student1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            student.setId(id);
            studentService.save(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        Student student1 = studentService.findById(id);
        if(student1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            studentService.deleteStudent(student1);
            return new ResponseEntity<>(student1, HttpStatus.OK);
        }
    }
}
