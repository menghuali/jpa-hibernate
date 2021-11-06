package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepoTests {
    @Autowired
    private CourseRepo repo;

    @Test
    @Order(1)
    public void testFindById() {
        Course course = repo.findById(1L);
        assertNotNull(course);
        assertEquals(1L, course.getId());
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @Order(2)
    public void testSave() {
        Course course = repo.save(new Course("New Class"));
        assertNotNull(course);
        assertEquals(2L, course.getId());
        assertEquals("New Class", course.getName());
    }

    @Test
    @Order(3)
    public void testDeleteById() {
        Course course = repo.deleteById(1L);
        assertNotNull(course);
        assertEquals(1L, course.getId());
        assertEquals("JPA in 50 Steps", course.getName());
    }

}
