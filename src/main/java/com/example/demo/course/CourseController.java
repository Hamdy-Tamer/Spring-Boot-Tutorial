package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseID}")
    public void deleteCourse(@PathVariable("courseID") int courseID){
        courseService.deleteCourse(courseID);
    }

    // PUT
    @PutMapping(path = "{courseID}")
    public void updateCourse(@PathVariable("courseID") int courseID, @RequestBody Course course){
        courseService.updateCourse(courseID, course);
    }

    // PATCH
    @PatchMapping(path = "{courseID}")
    public void updateCoursePatch(@PathVariable int courseID, @RequestBody Course course){
        courseService.patchCourse(courseID, course);
    }
}