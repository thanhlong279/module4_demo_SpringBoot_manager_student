package com.example.demo_spring_boot.services.impl;

import com.example.demo_spring_boot.models.Classroom;
import com.example.demo_spring_boot.repositories.IClassroomRepository;
import com.example.demo_spring_boot.services.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassroomService implements IClassroomService {
    @Autowired
    private IClassroomRepository classroomRepository;

    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }
}
