package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPQLTests {

    @Autowired
    private EntityManager em;


    @SuppressWarnings("all")
    @Test
    public void testQuery_Uptyped() {
       List result = em.createQuery("SELECT c FROM Course c").getResultList();
       assertEquals(1, result.size());
       Course course = (Course) result.get(0);
       assertNotNull(course);
       assertEquals(1L, course.getId());
    }

    @Test
    public void testQuery_Typed() {
       List<Course> result = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
       assertEquals(1, result.size());
       Course course = result.get(0);
       assertNotNull(course);
       assertEquals(1L, course.getId());
    }

    @Test
    public void testQuery_WhereClause() {
       List<Course> result = em.createQuery("SELECT c FROM Course c WHERE name like 'JPA in % Steps'", Course.class).getResultList();
       assertEquals(1, result.size());
       Course course = result.get(0);
       assertNotNull(course);
       assertEquals(1L, course.getId());
    }
    
}
