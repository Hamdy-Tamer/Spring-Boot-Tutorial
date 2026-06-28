package com.example.demo.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseRequest(

        @NotBlank(message = "Course name is required")
        @Pattern(
                regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$",
                message = "Course name must start with capital letters"
        )
        String course_name,


        @NotBlank(message = "Course code is required")
        @Pattern(
                regexp = "^[A-Z]{4}-\\d{3}$",
                message = "Course code format must be like JAVA-101"
        )
        String course_code

) {}