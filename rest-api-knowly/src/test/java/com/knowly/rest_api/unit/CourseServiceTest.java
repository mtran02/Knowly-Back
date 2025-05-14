package com.knowly.rest_api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.knowly.rest_api.entity.Course;
import com.knowly.rest_api.repository.CourseRepository;
import com.knowly.rest_api.repository.LessonRepository;
import com.knowly.rest_api.service.CourseService;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private CourseService courseService;

    private Course testCourse;

    @BeforeEach
    void setUp() {
        testCourse = new Course();
        testCourse.setId(1L);
        testCourse.setTitle("Test Course");
        testCourse.setDescription("Test Description");
    }

    @Test
    void createCourse_ShouldReturnSavedCourse() {
        Course courseToCreate = new Course();
        courseToCreate.setTitle("Test Course");
        courseToCreate.setDescription("Test Description");
        when(courseRepository.save(any(Course.class))).thenReturn(testCourse);

        Course result = courseService.createCourse(courseToCreate);

        assertNotNull(result);
        assertEquals("Test Course", result.getTitle());
        verify(courseRepository).save(courseToCreate);
    }

    @Test
    void getAllCourses_ShouldReturnListOfCourses() {

        when(courseRepository.findAll()).thenReturn(Arrays.asList(testCourse, testCourse));

        List<Course> result = courseService.getAllCourses();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(courseRepository).findAll();
    }
}