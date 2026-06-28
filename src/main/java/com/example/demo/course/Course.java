package com.example.demo.course;

import jakarta.persistence.*;

@Entity
@Table
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )

    private int courseID;
    private String course_name;
    private String course_code;

    public Course() {
    }

    public Course(String course_name, String course_code) {
        this.course_name = course_name;
        this.course_code = course_code;
    }

    public Course(int courseID, String course_name, String course_code) {
        this.courseID = courseID;
        this.course_name = course_name;
        this.course_code = course_code;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", course_name='" + course_name + '\'' +
                ", course_code='" + course_code + '\'' +
                '}';
    }
}