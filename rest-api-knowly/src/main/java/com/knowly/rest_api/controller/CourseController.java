package com.knowly.rest_api.controller;

import com.knowly.rest_api.controller.request.NewCourseRequest;
import com.knowly.rest_api.entity.Course;
import com.knowly.rest_api.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("{id}")
    public Optional<Course> getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody NewCourseRequest request) {
        try {
            courseService.addCourse(request);
            return ResponseEntity.status(201).body("Course created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create course: " + e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<String> putCourse(@RequestBody NewCourseRequest request, @PathVariable Long id) {
        try {
            courseService.updateCourse(id, request);
            return ResponseEntity.status(204).body("Course updated");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update course: " + e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.status(204).body("Course deleted");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update course: " + e.getMessage());
        }
    }
}
