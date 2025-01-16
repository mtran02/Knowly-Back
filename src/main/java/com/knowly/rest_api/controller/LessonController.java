package com.knowly.rest_api.controller;

import com.knowly.rest_api.controller.request.NewLessonRequest;
import com.knowly.rest_api.entity.Lesson;
import com.knowly.rest_api.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService){ this.lessonService = lessonService; }

    @GetMapping("/courses/{courseId}/lessons")
    public List<Lesson> getLessonsByCourseId(@PathVariable Long courseId){
        return lessonService.getLessonsByCourseId(courseId);
    }

    @GetMapping("/courses/{courseId}/lessons/{lessonId}")
    public Lesson getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }


    @PostMapping("/courses/{CourseId}/lessons")
    public ResponseEntity<String> addLesson(@RequestBody NewLessonRequest request, @PathVariable Long CourseId) {
        try {
            lessonService.addLesson(request,CourseId);
            return ResponseEntity.status(201).body("Lesson created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create lesson: " + e.getMessage());
        }
    }

    @PutMapping("/courses/{courseId}/lessons/{lessonId}")
    public ResponseEntity<String> putLesson(@RequestBody NewLessonRequest request, @PathVariable Long lessonId) {
        try {
            lessonService.updateLesson(lessonId,request);
            return ResponseEntity.status(200).body("Lesson updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update lesson: " + e.getMessage());
        }
    }

    @DeleteMapping("/courses/{courseId}/lessons/{lessonId}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long lessonId) {
        try {
            lessonService.deleteLesson(lessonId);
            return ResponseEntity.status(200).body("Lesson deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to delete lesson: " + e.getMessage());
        }
    }
}
