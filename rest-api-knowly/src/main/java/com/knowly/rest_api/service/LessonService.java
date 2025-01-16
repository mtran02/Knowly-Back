package com.knowly.rest_api.service;

import com.knowly.rest_api.controller.request.NewLessonRequest;
import com.knowly.rest_api.entity.Course;
import com.knowly.rest_api.entity.Lesson;
import com.knowly.rest_api.repository.CourseRepository;
import com.knowly.rest_api.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public void addLesson(NewLessonRequest request, Long courseId){
        Optional<Course> course = courseRepository.findById(courseId);

        if (course.isPresent()) {
            Lesson lesson = new Lesson();
            lesson.setContent(request.content());
            lesson.setCourse(course.get());

            lessonRepository.save(lesson);
        } else {
            throw new RuntimeException("Course not found with ID: " + courseId);
        }
    }

    public Lesson getLessonById(Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isPresent()) {
            return lesson.get();
        } else {
            throw new RuntimeException("Lesson not found with ID: " + id);
        }
    }

    public List<Lesson> getLessonsByCourseId(Long id){
        return lessonRepository.findByCourseId(id);
    }

    public void updateLesson(Long lessonId, NewLessonRequest request) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if (lesson.isPresent()){
            Lesson updateLesson = lesson.get();
            updateLesson.setId(lessonId);
            updateLesson.setContent(request.content());
            lessonRepository.save(updateLesson);
        }
    }

    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }
}
