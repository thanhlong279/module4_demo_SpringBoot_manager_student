package com.example.demo_spring_boot.repositories;
import com.example.demo_spring_boot.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {

//    Lưu ý: không viết dấu ; vào cuối câu SQL bởi vì JPA sẽ tự thêm các hậu tố như limit, order
    @Query(nativeQuery = true, value = "select * from student as s where s.name like :name")
////    Cập nhật dữ liệu thì thêm 2 annotation bên dưới
////    @Transactional
////    @Modifying
    Page<Student> findAllByNameContaining(@Param("name") String name, Pageable pageable);

    Student findStudentByIdIs(Long id);

}
