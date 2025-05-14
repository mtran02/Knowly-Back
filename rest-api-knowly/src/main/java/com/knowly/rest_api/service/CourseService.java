package com.knowly.rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowly.rest_api.controller.request.NewCourseRequest;
import com.knowly.rest_api.entity.Course;
import com.knowly.rest_api.repository.CourseRepository;
import com.knowly.rest_api.repository.LessonRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    public Course createCourse(Course course) {
        // Remove the courses list logic and use repository
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        // Remove the courses list logic and use repository
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public void addCourse(NewCourseRequest request) {
        Course course = new Course();
        course.setName(request.name());
        course.setPrice(request.price());
        courseRepository.save(course);
    }

    public void updateCourse(Long id, NewCourseRequest request) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Course updatedCourse = course.get();
            updatedCourse.setName(request.name());
            updatedCourse.setPrice(request.price());
            courseRepository.save(updatedCourse);
        }
    }

    public void deleteCourse(Long id) {
        lessonRepository.deleteByCourseId(id);
        courseRepository.deleteById(id);
    }
}
