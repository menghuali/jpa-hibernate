package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aloha.spring.jpahibernate.entity.Course;
import com.aloha.spring.jpahibernate.entity.Review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class OneToManyTests {

    @Autowired
    private CourseRepo repo;

    @Autowired
    private EntityManager em;

    @DirtiesContext
    @Transactional
    @Test
    public void testAddReviews() {
        Course course = repo.findById(1000L);
        assertNotNull(course);
        assertEquals(2, course.getReviews().size());

        Review review = new Review("4", "Very good!");
        review.setCourse(course);
        course.addReview(review);
        em.persist(review); // persist review and assoicate it with the course

        Course updated = repo.findById(1000L);
        assertEquals(3, updated.getReviews().size());
    }
    
}
