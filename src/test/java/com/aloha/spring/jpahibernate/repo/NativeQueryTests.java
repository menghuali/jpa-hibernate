package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("unchecked")
@SpringBootTest
public class NativeQueryTests {

    @Autowired
    private EntityManager em;

    @Test
    public void testNativeQuery() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE_DETAILS", Course.class);
        List<Course> results = query.getResultList();
        assertEquals(3, results.size());
    }

    @Test
    public void testNativeQuery_WhereClouse_PositionedParam() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE_DETAILS WHERE id=?1", Course.class);
        query.setParameter(1, 1000L);
        List<Course> results = query.getResultList();
        assertEquals(1, results.size());
    }

    @Test
    public void testNativeQuery_WhereClouse_NamedParam() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE_DETAILS WHERE id=:id", Course.class);
        query.setParameter("id", 1000L);
        List<Course> results = query.getResultList();
        assertEquals(1, results.size());
    }

    @Transactional
    @Test
    public void update() {
        Query query = em.createNativeQuery("UPDATE COURSE_DETAILS SET last_updated_time=SYSDATE()", Course.class);
        assertEquals(3, query.executeUpdate());
    }

}
