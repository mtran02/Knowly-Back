package com.knowly.rest_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowly.rest_api.controller.request.NewCourseRequest;
import com.knowly.rest_api.entity.Course;
import com.knowly.rest_api.service.CourseService;

/**
 * controleur REST pour gerer les cours.
 * cette classe fournit des endpoints pour céer, lire, mettre a jour et supprimer des cours
 */

@RestController
@RequestMapping("courses")
public class CourseController {
    private final CourseService courseService;
    
    /**
     * constructeur pour initialiser le CourseController avec le service de cours.
     * @param courseService le service de cours utilisé pour gérer les operations cours
     */

    public CourseController(CourseService courseService)
    { 
        this.courseService = courseService; 
    }
    
    /**
     * récupere la liste de tous les cours.
     * @return la liste de tous les cours disponibles.
     */

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    /**
     * récupere un cours par son id.
     * 
     * @param id de l'identifiant du cours a récuperer.
     * @return le cours correspondant a l'identifiant.
     */
    @GetMapping("{id}")
    public Optional<Course> getCourse(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    /**
     * ajoute un nouveau cours.
     * 
     * @param request l'objet contenant les informations du nouveau cours a creer.
     * @return reponse HTTP indiquant le resultat de l'operation.
     */

    @PostMapping
    public ResponseEntity<String> addCourse(@RequestBody NewCourseRequest request){
        try {
            courseService.addCourse(request);
            return ResponseEntity.status(201).body("Course created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create course: " + e.getMessage());
        }
    }
    
    /**
     * Met à jour un cours existant.
     *
     * @param request L'objet contenant les nouvelles informations du cours.
     * @param id      L'identifiant du cours à mettre à jour.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     */
    @PutMapping("{id}")
    public ResponseEntity<String> putCourse(@RequestBody NewCourseRequest request, @PathVariable Long id){
        try {
            courseService.updateCourse(id,request);
            return ResponseEntity.status(204).body("Course updated");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update course: " + e.getMessage());
        }
    }
    
    /**
     * supprime un cours par son id 
     * 
     * @param  id l'identifiant du cours a supprimer
     * @return reponse HTTP indiquant le resultat de l'operation.
     */

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.status(200).body("Course deleted");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to update course: " + e.getMessage());
        }
    }
}
