package com.example.demo.course;

public record CoursePatchRequest(
        String course_name,
        String course_code
) {}