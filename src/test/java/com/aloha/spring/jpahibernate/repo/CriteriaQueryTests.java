package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CriteriaQueryTests {
    
    @Autowired
    private EntityManager em;

    @Test
    public void simpleQuery() {
        // Query to build: SELECT c from Course c WHERE c.name LIKE '%100 Steps'

        // 1. Use Criteria Builder to build a query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invovled in query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define conditions
        Predicate predicate = cb.like(courseRoot.get("name"), "%100 Steps");
        cq.where(predicate);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        assertEquals(1, courses.size());
        Course course = courses.get(0);
        assertEquals("Become A Bat in 100 Steps", course.getName());
    }

    @Test
    public void courseWithoutStudent() {
        // Query to build: SELECT c from Course c WHERE c.students IS EMPTY
        // 1. Use Criteria Builder to build a query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invovled in query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define conditions
        Predicate predicate = cb.isEmpty(courseRoot.get("students"));
        cq.where(predicate);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        assertEquals(1, courses.size());
        Course course = courses.get(0);
        assertEquals(2000L, course.getId());
    }

    @Test
    public void join() {
        // Query to build: SELECT c, s from Course c JOIN c.students s
        // 1. Use Criteria Builder to build a query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invovled in query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define JOIN
        courseRoot.join("students");

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        assertEquals(5, courses.size());
    }

    @Test
    public void leftJoin() {
        // Query to build: SELECT c, s from Course c JOIN c.students s
        // 1. Use Criteria Builder to build a query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are invovled in query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define JOIN
        courseRoot.join("students", JoinType.LEFT);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> courses = query.getResultList();
        assertEquals(6, courses.size());
    }

}
