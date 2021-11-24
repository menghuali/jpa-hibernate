package com.aloha.spring.jpahibernate.repo.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

// @DataJpaTest (it doesn't work with tests of saving/updating)
@SpringBootTest
public class CourseRepoJpaDataRepoTests {

    @Autowired
    private CourseRepoJpaDataRepo repo;

    @Test
    public void findById_Found() {
        assertTrue(repo.findById(1000L).isPresent(), "Course 1000 should be presented");
    }

    @Test
    public void findById_NotFound() {
        assertFalse(repo.findById(3000L).isPresent(), "Course 3000 shouldn't be presented");
    }

    @Test
    @DirtiesContext
    public void saveAndUpdate() {
        Course course = new Course("New Class");
        course = repo.save(course);
        LocalDateTime updatedTime = course.getLastUpdatedTime();
        course.setName("New Casss - Updated");
        course = repo.save(course);
        assertTrue(updatedTime.compareTo(course.getLastUpdatedTime()) < 0);
    }

    @Test
    public void count() {
        assertEquals(4, repo.count(), "Count of Course isn't expected");
    }

    @Test
    public void sort() {
        List<Course> courses = repo.findAll(Sort.by(Sort.Direction.DESC, "name"));
        assertEquals("Become A superhero in X Steps", courses.get(0).getName());
        assertEquals("Become A Superman in 200 Steps", courses.get(1).getName());
        assertEquals("Become A Spider in 50 Steps", courses.get(2).getName());
        assertEquals("Become A Bat in 100 Steps", courses.get(3).getName());
    }

    @Test
    @DirtiesContext
    public void pagination() {
        // repo.deleteAll();
        for (int i = 0; i < 100; i++)
            repo.save(new Course("Course - " + i));

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Course> page = repo.findAll(pageRequest);
        assertEquals(20, page.getSize());
        assertTrue(page.hasNext());

        int pageCount = 1;
        while (page.hasNext()) {
            Pageable pageable = page.nextPageable();
            page = repo.findAll(pageable);
            assertTrue(page.getSize() > 0 && page.getSize() <= 20);
            pageCount++;
        }
        assertEquals(6, pageCount);
    }

}
