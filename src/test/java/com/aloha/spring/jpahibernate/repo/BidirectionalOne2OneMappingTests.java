package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Passport;
import com.aloha.spring.jpahibernate.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BidirectionalOne2OneMappingTests {

    @Autowired
    private EntityManager em;

    @Test
    public void testGetStudentThroughPassport() {
        Passport passport = em.find(Passport.class, 1000L);
        assertEquals("MARVEL_1", passport.getNumber());
        Student student = passport.getStudent();
        assertNotNull(student);
        assertEquals("Peter Parker", student.getName());
    }
    
}
