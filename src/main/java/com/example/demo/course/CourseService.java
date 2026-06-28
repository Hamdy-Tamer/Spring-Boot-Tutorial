package com.example.demo.course;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course){
        Optional<Course> courseOptional = courseRepository.findCourseByCourseCode(course.getCourse_code());

        if(courseOptional.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Course code is taken"
            );
        }
        courseRepository.save(course);
    }

    public void deleteCourse(int courseID){
        boolean exists = courseRepository.existsById(courseID);

        if(!exists){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course with id " + courseID + " doesn't exist"
            );
        }
        courseRepository.deleteById(courseID);
    }

    // PUT
    @Transactional
    public void updateCourse(int courseID, Course updatedCourse){
        Course course =
                courseRepository.findById(courseID)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Course with id " + courseID + " doesn't exist"
                                ));

        if(updatedCourse.getCourse_name() != null && !Objects.equals(course.getCourse_name(),
                updatedCourse.getCourse_name())){
            course.setCourse_name(updatedCourse.getCourse_name());
        }

        if(updatedCourse.getCourse_code() != null && !Objects.equals(
                        course.getCourse_code(),
                        updatedCourse.getCourse_code())){
            Optional<Course> courseOptional =
                    courseRepository.findCourseByCourseCode(
                            updatedCourse.getCourse_code()
                    );

            if(courseOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Course code is taken"
                );
            }

            course.setCourse_code(
                    updatedCourse.getCourse_code()
            );
        }
    }

    // PATCH
    @Transactional
    public void patchCourse(int courseID, Course updatedCourse){
        Course course =
                courseRepository.findById(courseID)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Course with id " + courseID + " doesn't exist"
                                ));

        if(updatedCourse.getCourse_name() != null){
            course.setCourse_name(updatedCourse.getCourse_name());
        }

        if(updatedCourse.getCourse_code() != null && !Objects.equals(
                        course.getCourse_code(),
                        updatedCourse.getCourse_code()
                )){

            Optional<Course> courseOptional = courseRepository.findCourseByCourseCode(updatedCourse.getCourse_code());

            if(courseOptional.isPresent()){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Course code is taken"
                );
            }
            course.setCourse_code(updatedCourse.getCourse_code());
        }
    }
}