Feature: Lesson Management

  Scenario: Create a new lesson
    Given I have lesson details with title "Introduction" and content "Welcome to the course"
    When I create a new lesson
    Then the lesson should be saved successfully

  Scenario: Get all lessons
    Given there are existing lessons in the system
    When I request all lessons
    Then I should receive a list of lessons