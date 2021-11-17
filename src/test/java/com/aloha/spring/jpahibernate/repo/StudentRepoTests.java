package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.aloha.spring.jpahibernate.entity.Course;
import com.aloha.spring.jpahibernate.entity.Passport;
import com.aloha.spring.jpahibernate.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class StudentRepoTests {

    @Autowired
    private StudentRepo repo;

    @DirtiesContext
    @Test
    public void testSaveStudent_WithPassport() {
        Passport passport = new Passport("MARVEL_2");
        Student student = new Student("Tony Stark", passport);
        student = repo.save(student);
        assertEquals(1003L, student.getId());
        assertEquals(1003L, passport.getId());
    }

    @DirtiesContext
    @Test
    public void testSaveStudent_WithoutPassport() {
        Student student = new Student("Tony Stark");
        student = repo.save(student);
        assertEquals(1003L, student.getId());
    }

    /**
     * Sample of Eager Fetching
     */
    @Transactional
    @Test
    public void testFindById() {
        Student student = repo.findById(1000L);
        assertNotNull(student);
        assertEquals("Peter Parker", student.getName());
        Passport passport = student.getPassport();
        assertNotNull(passport);
        assertEquals("MARVEL_1", passport.getNumber()); // Lazy Fetch will cause exception at this line IF the method is
                                                        // NOT annotatned with Transactional.
    }

    @Transactional
    @Test
    public void testFindStudentCourse() {
        Student student = repo.findById(1000L);
        List<Course> courses = student.getCourses();
        assertEquals(3, courses.size());
    }

}
