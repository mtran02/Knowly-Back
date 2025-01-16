package com.knowly.rest_api.repository;

import com.knowly.rest_api.entity.Lesson;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseId(Long courseId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Lesson l WHERE l.courseId = :courseId")
    void deleteByCourseId(@Param("courseId") Long courseId);

}
