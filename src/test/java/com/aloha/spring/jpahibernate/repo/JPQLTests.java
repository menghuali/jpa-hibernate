package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Course;
import com.aloha.spring.jpahibernate.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class JPQLTests {

   @Autowired
   private EntityManager em;

   @SuppressWarnings("all")
   @Test
   public void testQuery_Uptyped() {
      List courses = em.createQuery("SELECT c FROM Course c").getResultList();
      assertEquals(4, courses.size());
   }

   @Test
   public void testQuery_Typed() {
      List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
      assertEquals(4, courses.size());
   }

   @Test
   public void testQuery_WhereClause() {
      List<Course> courses = em.createQuery("SELECT c FROM Course c WHERE name LIKE '%Bat%'", Course.class)
            .getResultList();
      assertEquals(1, courses.size());
      Course course = courses.get(0);
      assertNotNull(course);
      assertEquals(1001L, course.getId());
   }

   @Test
   public void testNamedQuery_getAllCourses() {
      List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class).getResultList();
      assertEquals(4, courses.size());
   }

   @Test
   public void testNamedQuery_findCourseByName() {
      List<Course> courses = em.createNamedQuery("query_find_course_spider", Course.class).getResultList();
      assertEquals(1, courses.size());
      Course course = courses.get(0);
      assertEquals(1000L, course.getId());
   }

   @Test
   public void testQuery_FindCourseWithoutStudent() {
      List<Course> courses = em.createQuery("SELECT c FROM Course c WHERE c.students IS EMPTY", Course.class)
            .getResultList();
      assertEquals(1, courses.size());
      Course course = courses.get(0);
      assertEquals(2000, course.getId());
   }

   @Test
   public void testQuery_FindCourseWithAtLeastTwoStudent() {
      List<Course> courses = em.createQuery("SELECT c FROM Course c WHERE SIZE(c.students) >= 2", Course.class)
            .getResultList();
      assertEquals(2, courses.size());
      HashSet<Long> courseIDs = new HashSet<>();
      courses.forEach(e -> courseIDs.add(e.getId()));
      assertEquals(2, courseIDs.size());
      assertTrue(courseIDs.contains(1001L));
      assertTrue(courseIDs.contains(1002L));
   }

   @Test
   public void testQuery_FindCourseOrderByStudentSize() {
      List<Course> courses = em.createQuery("SELECT c FROM Course c ORDER BY SIZE(c.students)", Course.class)
            .getResultList();
      assertEquals(2000L, courses.get(0).getId());
      assertEquals(1000L, courses.get(1).getId());
      assertEquals(1001L, courses.get(2).getId());
      assertEquals(1002L, courses.get(3).getId());
   }

   @Test
   public void testQuery_FindMarvelStudents() {
      List<Student> students = em
            .createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE 'MARVEL%'", Student.class)
            .getResultList();
      assertEquals(1, students.size());
      Student student = students.get(0);
      assertEquals("Peter Parker", student.getName());
   }

   @SuppressWarnings("all")
   @Transactional
   @Test
   public void testJoin() {
      List<Object[]> result = em.createQuery("SELECT c, s FROM Course c JOIN c.students s").getResultList();
      assertEquals(5, result.size());
      result.forEach(e -> {
         Course course = (Course) e[0];
         List<Student> students = course.getStudents();
         assertNotNull(students);
         assertFalse(students.isEmpty());
      });
   }

   @SuppressWarnings("all")
   @Transactional
   @Test
   public void testLeftJoin() {
      List<Object[]> result = em.createQuery("SELECT c, s FROM Course c LEFT JOIN c.students s").getResultList();
      assertEquals(6, result.size());

      Course course2k = null;
      for (Object[] objects : result) {
         Course course = (Course) objects[0];
         if (course.getId() == 2000L) {
            course2k = course;
            break;
         } else
            assertFalse(course.getStudents().isEmpty());
      }
      assertNotNull(course2k);
      List<Student> students = course2k.getStudents();
      assertTrue(students.isEmpty());
   }

   @SuppressWarnings("all")
   @Transactional
   @Test
   public void testCrossJoin() {
      List<Object[]> result = em.createQuery("SELECT c, s FROM Course c, Student s").getResultList();
      assertEquals(12, result.size());
   }

}
