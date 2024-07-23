package com.example.demo_spring_boot.controllers;

import com.example.demo_spring_boot.dtos.StudentDTO;
import com.example.demo_spring_boot.models.Classroom;
import com.example.demo_spring_boot.models.Student;
import com.example.demo_spring_boot.services.IClassroomService;
import com.example.demo_spring_boot.services.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//Đánh dấu đây là 1 controller (1 bean)
//Annotation Controller
//Bài tập về nhà: phân biệt @Component, @Controller, @Service, @Repository (đều sử dụng để tạo bean)
@Controller
@RequestMapping(value = "student")
public class StudentController {

//    Cơ chế DI (Dependence injection): Tiêm phụ thuộc để giảm sự phụ thuộc
//    DI: Field/Interface
//    DI: Setter
//    DI: Constructor

    //    Scope và vòng đời của Bean
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassroomService classroomService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("")
    public String displayAllStudents(Model model,
                                     @RequestParam(value = "nameStudent", defaultValue = "") String nameStudent,
                                     @RequestParam(value = "page", defaultValue = "0") int page) {
        Sort sort = Sort.by("name").ascending();
        Page<Student> students = studentService.findAllByName(nameStudent, PageRequest.of(page, 5, sort));
        model.addAttribute("students", students);
        model.addAttribute("nameStudent", nameStudent);
        return "student/list";
    }

    //    @RequestMapping (value = "/create", method = RequestMethod.GET)
    @GetMapping("/create")
    public String viewCreate(Model model) {
        model.addAttribute("studentDTO", new StudentDTO());
        model.addAttribute("classrooms", classroomService.getAllClassrooms());
        return "student/create";
    }

    @PostMapping("/create")
    public String newStudent(@Validated @ModelAttribute("studentDTO") StudentDTO studentDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirect, Model model) {
        new StudentDTO().validate(studentDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("classrooms", classroomService.getAllClassrooms());
            return "student/create";
        }
        Student student = new Student();
//        studentCreate.setName(student.getName());
//        studentCreate.setAddress(student.getAddress());
//        studentCreate.setScore(student.getScore());
//        studentCreate.setClassroom(student.getClassroom());
        // muon copy: trung kieu du lieu va trung ten
        BeanUtils.copyProperties(studentDTO, student);
        studentService.save(student);
        redirect.addFlashAttribute("message", "Thêm mới thành công");
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String viewUpdate(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/update";
    }

    //    @PostMapping("/update")
//    public String updateStudent(@ModelAttribute("student") Student student,
//                                RedirectAttributes redirectAttributes){
//            studentService.update(student.getId(), student);
//            redirectAttributes.addFlashAttribute("message", "sửa thông tin thành công");
//        return "redirect:/student";
//
//    }
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student,
                                RedirectAttributes redirectAttributes) {
        try {
            studentService.update(student);
            redirectAttributes.addFlashAttribute("message", "Sửa thông tin thành công.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy sinh viên.");
        }
        return "redirect:/student";
    }

//    @GetMapping("/search")
//    public String searchStudent(@RequestParam("nameStudent") String nameStudent, Model model) {
//        List<Student> students = studentService.findAllByName(nameStudent);
//        model.addAttribute("students", students);
//        model.addAttribute("nameStudent", nameStudent);
//        return "student/list";
//    }


}
