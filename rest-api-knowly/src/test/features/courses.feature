Feature: Course Management

Scenario: Create a new course
    Given I have course details with title "Java Programming" and description "Learn Java"
    When I create a new course
    Then the course should be saved successfully

Scenario: Get all courses
    Given there are existing courses in the system
    When I request all courses
    Then I should receive a list of courses