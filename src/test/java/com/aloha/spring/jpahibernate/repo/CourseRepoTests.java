package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    public void testSave_Persist() {
        Course course = repo.save(new Course("New Class"));
        assertNotNull(course);
        assertEquals(3L, course.getId());
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

}
