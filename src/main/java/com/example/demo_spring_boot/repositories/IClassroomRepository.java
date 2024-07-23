package com.example.demo_spring_boot.repositories;

import com.example.demo_spring_boot.models.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassroomRepository extends JpaRepository<Classroom, Long> {
}
