package com.knowly.rest_api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.knowly.rest_api.entity.Lesson;
import com.knowly.rest_api.repository.CourseRepository;
import com.knowly.rest_api.repository.LessonRepository;
import com.knowly.rest_api.service.LessonService;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private LessonService lessonService;

    private Lesson testLesson;

    @BeforeEach
    void setUp() {
        testLesson = new Lesson();
        testLesson.setId(1L);
        testLesson.setTitle("Test Lesson");
        testLesson.setContent("Test Content");
    }

    @Test
    void createLesson_ShouldReturnSavedLesson() {
        Lesson lessonToCreate = new Lesson();
        lessonToCreate.setTitle("Test Lesson");
        lessonToCreate.setContent("Test Content");
        when(lessonRepository.save(any(Lesson.class))).thenReturn(testLesson);

        Lesson result = lessonService.createLesson(lessonToCreate);

        assertNotNull(result);
        assertEquals("Test Lesson", result.getTitle());
        verify(lessonRepository).save(lessonToCreate);
    }

    @Test
    void getAllLessons_ShouldReturnListOfLessons() {
        when(lessonRepository.findAll()).thenReturn(Arrays.asList(testLesson, testLesson));

        List<Lesson> result = lessonService.getAllLessons();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(lessonRepository).findAll();
    }

    @Test
    void getLessonById_WhenLessonExists_ShouldReturnLesson() {
        Lesson lesson = new Lesson("Test Lesson", "Test Content");
        lesson.setId(1L);
        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));

        Lesson result = lessonService.getLessonById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Lesson", result.getTitle());
    }

    @Test
    void deleteLesson_ShouldCallRepository() {
        Long lessonId = 1L;

        lessonService.deleteLesson(lessonId);

        verify(lessonRepository).deleteById(lessonId);
    }
}