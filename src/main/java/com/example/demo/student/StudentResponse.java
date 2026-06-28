package com.example.demo.student;

import java.time.LocalDate;


public record StudentResponse(
        Long id,
        String name,
        String email,
        LocalDate dob,
        int age
) {

    public static StudentResponse fromStudent(Student student){
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDob(),
                student.computeAge()
        );
    }
}