package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;

import com.aloha.spring.jpahibernate.entity.Course;
import com.aloha.spring.jpahibernate.entity.Review;
import com.aloha.spring.jpahibernate.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CourseRepoTests {
    @Autowired
    private CourseRepo repo;

    @Test
    public void testFindById() {
        Course course = repo.findById(1000L);
        assertNotNull(course);
        assertEquals(1000L, course.getId());
        assertEquals("Become A Spider in 50 Steps", course.getName());
    }

    @DirtiesContext
    @Test
    public void testSave_Persist() {
        Course course = repo.save(new Course("New Class"));
        assertNotNull(course);
        assertEquals(1003L, course.getId());
        assertEquals("New Class", course.getName());
    }

    @DirtiesContext
    @Test
    public void testSave_Merge() {
        Course course = repo.findById(1000L);
        assertNotNull(course);
        course.setName("New Class");
        repo.save(course);
        course = repo.findById(1000L);
        assertEquals("New Class", course.getName());
    }

    @DirtiesContext
    @Test
    public void testDeleteById() {
        Course course = repo.deleteById(2000L);
        assertNotNull(course);
        assertEquals(2000L, course.getId());
        assertEquals("Become A superhero in X Steps", course.getName());
        assertNull(repo.findById(2000L));
    }

    @DirtiesContext
    @Test
    public void testPlayWithEntityManager_1() {
        Course course = repo.playWithEntityManager();
        assertEquals("[Update-1]Play with Entity Manager - 1", course.getName());
    }

    @DirtiesContext
    @Test
    public void testPlayWithEntityManager_2() {
        Course course = repo.playWithEntityManagerRefresh();
        assertEquals("Play with Entity Manager - 2", course.getName());
    }

    @Test
    public void testNameNullable() {
        try {
            repo.save(new Course());
        } catch (DataIntegrityViolationException e) {
            return;
        }
        fail("Exception should occur");
    }

    @DirtiesContext
    @Test
    public void testCreatedTime() {
        Course course = repo.save(new Course("New Course"));
        assertNotNull(course.getCreatedTime());
        assertNotNull(course.getLastUpdatedTime());
    }

    @DirtiesContext
    @Test
    public void testLastUpdatedTime() throws InterruptedException {
        Course course = repo.findById(1000L);
        LocalDateTime updatedTime1 = course.getLastUpdatedTime();
        course.setName(course.getName() + " Updated");
        repo.save(course);
        course = repo.findById(1000L);
        assertTrue(updatedTime1.compareTo(course.getLastUpdatedTime()) < 0);
    }

    @Transactional
    @Test
    public void findCourseReviews() {
        Course course = repo.findById(1001L);
        List<Review> reviews = course.getReviews();
        assertEquals(2, reviews.size());
    }

    @Transactional
    @Test
    public void findCourseStudents() {
        Course course = repo.findById(1001L);
        List<Student> students = course.getStudents();
        assertEquals(2, students.size());
    }

    // This annotation makes two query in same transaction and first level cache is
    // used
    @Transactional
    @Test
    public void testFindById_firstLevelCacheDemo() {
        Course course1 = repo.findById(1000L);
        log.info("First query: {}", course1);
        assertNotNull(course1);

        Course course2 = repo.findById(1000L);
        log.info("Second query: {}", course2);
        assertNotNull(course2);
    }

}
