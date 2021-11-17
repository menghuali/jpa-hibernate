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
      assertEquals(4, result.size());
   }

   @Test
   public void testQuery_Typed() {
      List<Course> result = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
      assertEquals(4, result.size());
   }

   @Test
   public void testQuery_WhereClause() {
      List<Course> result = em.createQuery("SELECT c FROM Course c WHERE name LIKE '%Bat%'", Course.class)
            .getResultList();
      assertEquals(1, result.size());
      Course course = result.get(0);
      assertNotNull(course);
      assertEquals(1001L, course.getId());
   }

   @Test
   public void testNamedQuery_getAllCourses() {
      List<Course> result = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
      assertEquals(4, result.size());
   }

   @Test
   public void testNamedQuery_findCourseByName() {
      List<Course> result = em.createNamedQuery("query_find_course_spider", Course.class).getResultList();
      assertEquals(1, result.size());
      Course course = result.get(0);
      assertEquals(1000L, course.getId());
   }

}
