package com.example.demo_spring_boot.dtos;

import com.example.demo_spring_boot.models.Classroom;
import jakarta.validation.constraints.*;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class StudentDTO implements Validator {
    @NotBlank(message = "ten ko dc de trong")
    @Pattern(regexp = "^[A-Za-z ]{2,100}$", message = "ten ko dung dinh dang")
    private String name;
//    @Size(min = 10, max = 255, message = "dia chi ko dc vuot qua 255 va it hon 10 ky tu")
    private String address;
    @Min(value = 0, message = "diem ko dc nho hon 0")
    @Max(value = 10, message = "diem ko dc lon hon 10")
    @NotNull(message = "diem ko dc de trong")
    private Float score;
    @NotNull(message = "lop ko dc de trong")
    private Classroom classroom;

    public StudentDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudentDTO studentDTO = (StudentDTO) target;
        if (studentDTO.address.length()<10){
            errors.rejectValue("address", "", "dia chi ko dc nho hon 10 ky ty");
        }else if(studentDTO.address.length()>255){
            errors.rejectValue("address", "", "dia chi ko dc lon hon 255 ky ty");
        }
    }
}
