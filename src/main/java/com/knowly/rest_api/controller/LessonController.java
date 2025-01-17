package com.knowly.rest_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowly.rest_api.controller.request.NewLessonRequest;
import com.knowly.rest_api.entity.Lesson;
import com.knowly.rest_api.service.LessonService;

/**
 * Contrôleur REST pour gérer les leçons.
 * Cette classe fournit des endpoints pour créer, lire, mettre à jour et supprimer des leçons associées à des cours.
 */
@RestController
@RequestMapping
public class LessonController {
    private final LessonService lessonService;
    
    /**
     * Constructeur pour initialiser le LessonController avec le service de leçons.
     *
     * @param lessonService Le service de leçons utilisé pour gérer les opérations sur les leçons.
     */
    public LessonController(LessonService lessonService){ this.lessonService = lessonService; }
    
    /**
     * Récupere la liste des lecons pour un cours donné.
     * 
     * @param courseId L'identifiant du cours dont on souhaite récupérer les leçons.
     * @return Une liste de leçons associées au cours spécifié.
     */
    @GetMapping("/courses/{courseId}/lessons")
    public List<Lesson> getLessonsByCourseId(@PathVariable Long courseId){
        return lessonService.getLessonsByCourseId(courseId);
    }
    
    /**
     * Récupère une leçon par son identifiant.
     *
     * @param lessonId L'identifiant de la leçon à récupérer.
     * @return La leçon correspondant à l'identifiant fourni.
     */
    @GetMapping("/courses/{courseId}/lessons/{lessonId}")
    public Lesson getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    
    /**
     * Ajoute une nouvelle leçon à un cours.
     *
     * @param request  L'objet contenant les informations de la nouvelle leçon à créer.
     * @param courseId L'identifiant du cours auquel la leçon sera ajoutée.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     */
    @PostMapping("/courses/{CourseId}/lessons")
    public ResponseEntity<String> addLesson(@RequestBody NewLessonRequest request, @PathVariable Long CourseId) {
        try {
            lessonService.addLesson(request,CourseId);
            return ResponseEntity.status(201).body("Lesson created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create lesson: " + e.getMessage());
        }
    }
    /**
     * Met à jour une leçon existante.
     *
     * @param request  L'objet contenant les nouvelles informations de la leçon.
     * @param lessonId L'identifiant de la leçon à mettre à jour.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     */
    @PutMapping("/courses/{courseId}/lessons/{lessonId}")
    public ResponseEntity<String> putLesson(@RequestBody NewLessonRequest request, @PathVariable Long lessonId) {
        try {
            lessonService.updateLesson(lessonId,request);
            return ResponseEntity.status(204).body("Lesson updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update lesson: " + e.getMessage());
        }
    }
    
    /**
     * Supprime une leçon par son identifiant.
     *
     * @param lessonId L'identifiant de la leçon à supprimer.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     */
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
