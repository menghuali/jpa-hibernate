package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class CourseRepoTests {
    @Autowired
    private CourseRepo repo;

    @Test
    public void testFindById() {
        Course course = repo.findById(1L);
        assertNotNull(course);
        assertEquals(1L, course.getId());
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @DirtiesContext
    @Test
    public void testSave_Persist() {
        Course course = repo.save(new Course("New Class"));
        assertNotNull(course);
        assertEquals(2L, course.getId());
        assertEquals("New Class", course.getName());
    }

    @DirtiesContext
    @Test
    public void testSave_Merge() {
        Course course = repo.findById(1L);
        assertNotNull(course);
        course.setName("New Class");
        repo.save(course);
        course = repo.findById(1L);
        assertEquals("New Class", course.getName());
    }

    @DirtiesContext
    @Test
    public void testDeleteById() {
        Course course = repo.deleteById(1L);
        assertNotNull(course);
        assertEquals(1L, course.getId());
        assertEquals("JPA in 50 Steps", course.getName());
        assertNull(repo.findById(1L));
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
        Course course = repo.playWithEntityManager_Refresh();
        assertEquals("Play with Entity Manager - 2", course.getName());
    }

    @Test
    public void testNameNullable() {
        try {
            repo.save(new Course());
            fail("Exception should occur");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }
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
        Course course = repo.findById(1L);
        LocalDateTime updatedTime1 = course.getLastUpdatedTime();
        Thread.sleep(200);
        course.setName(course.getName() + " Updated");
        repo.save(course);

        course = repo.findById(1L);
        assertTrue(updatedTime1.compareTo(course.getLastUpdatedTime()) < 0);
    }

}
