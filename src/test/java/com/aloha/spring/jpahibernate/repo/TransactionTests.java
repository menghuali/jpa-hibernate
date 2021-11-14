package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aloha.spring.jpahibernate.entity.Passport;
import com.aloha.spring.jpahibernate.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionTests {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void testMultipleOperations() {
        // Transaction begins
        Student student = em.find(Student.class, 1000L);
        assertNotNull(student);

        Passport passport = student.getPassport();
        passport.setNumber("SONY-1");

        student.setName("Sipder Man");
        // Transaction ends
    }

}
