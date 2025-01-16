package com.knowly.rest_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = false)
    @JsonBackReference
    private Course course;

    public Lesson() {}

    public Lesson(String content, Course course) {
        this.content = content;
        this.course = course;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCourse(Course course) {
        this.course = course;
        this.courseId = course != null ? course.getId() : null;
    }
}
