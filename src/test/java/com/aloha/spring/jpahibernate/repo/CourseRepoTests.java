package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        assertNotNull(course.getId());
        assertTrue(course.getId() > 0);
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

}
