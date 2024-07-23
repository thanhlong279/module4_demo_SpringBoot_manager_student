package com.example.demo_spring_boot.services;

import com.example.demo_spring_boot.models.Classroom;

import java.util.List;

public interface IClassroomService {
    List<Classroom> getAllClassrooms();
}
