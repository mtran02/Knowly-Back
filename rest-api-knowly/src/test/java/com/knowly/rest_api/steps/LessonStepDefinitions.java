package com.knowly.rest_api.steps;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.knowly.rest_api.entity.Lesson;
import com.knowly.rest_api.service.LessonService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LessonStepDefinitions {

    @Autowired
    private LessonService lessonService;

    private Lesson testLesson;
    private List<Lesson> lessonList;
    private Exception lastException;

    @Given("I have lesson details with title {string} and content {string}")
    public void iHaveLessonDetails(String title, String content) {
        testLesson = new Lesson();
        testLesson.setTitle(title);
        testLesson.setContent(content);
    }

    @When("I create a new lesson")
    public void iCreateANewLesson() {
        try {
            testLesson = lessonService.createLesson(testLesson);
        } catch (Exception e) {
            lastException = e;
        }
    }

    @Then("the lesson should be saved successfully")
    public void theLessonShouldBeSavedSuccessfully() {
        assertNull(lastException);
        assertNotNull(testLesson);
        assertNotNull(testLesson.getId());
    }

    @Given("there are existing lessons in the system")
    public void thereAreExistingLessonsInTheSystem() {
        lessonService.createLesson(new Lesson("Introduction", "Welcome to the course"));
        lessonService.createLesson(new Lesson("Chapter 1", "Basic concepts"));
    }

    @When("I request all lessons")
    public void iRequestAllLessons() {
        lessonList = lessonService.getAllLessons();
    }

    @Then("I should receive a list of lessons")
    public void iShouldReceiveAListOfLessons() {
        assertNotNull(lessonList);
        assertFalse(lessonList.isEmpty());
    }
}