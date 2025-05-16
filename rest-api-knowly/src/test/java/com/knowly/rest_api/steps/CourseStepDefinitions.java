package com.knowly.rest_api.steps;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.knowly.rest_api.entity.Course;
import com.knowly.rest_api.service.CourseService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class CourseStepDefinitions {

    @Autowired
    private CourseService courseService;

    private Course testCourse;
    private List<Course> courseList;
    private Exception lastException;

    @Given("I have course details with title {string} and description {string}")
    public void iHaveCourseDetails(String title, String description) {
        testCourse = new Course();
        testCourse.setTitle(title);
        testCourse.setDescription(description);
    }

    @When("I create a new course")
    public void iCreateANewCourse() {
        try {
            testCourse = courseService.createCourse(testCourse);
        } catch (Exception e) {
            lastException = e;
        }
    }

    @Then("the course should be saved successfully")
    public void theCourseShouldBeSavedSuccessfully() {
        assertNull(lastException);
        assertNotNull(testCourse);
        assertNotNull(testCourse.getId());
    }

    @Given("there are existing courses in the system")
    public void thereAreExistingCoursesInTheSystem() {
        Course course1 = new Course();
        course1.setTitle("Java Course");
        course1.setDescription("Learn Java");
        courseService.createCourse(course1);

        Course course2 = new Course();
        course2.setTitle("Spring Course");
        course2.setDescription("Learn Spring");
        courseService.createCourse(course2);
    }

    @When("I request all courses")
    public void iRequestAllCourses() {
        try {
            courseList = courseService.getAllCourses();
        } catch (Exception e) {
            lastException = e;
        }
    }

    @Then("I should receive a list of courses")
    public void iShouldReceiveAListOfCourses() {
        assertNull(lastException);
        assertNotNull(courseList);
        assertTrue(courseList.size() >= 2);
    }
}