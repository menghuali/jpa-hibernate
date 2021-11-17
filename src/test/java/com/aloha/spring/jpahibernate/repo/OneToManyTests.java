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
    @Transactional // it's not needed if going with EAGER fetch
    @Test
    public void testAddReviews() {
        Course current = repo.findById(1001L);
        assertEquals(2, current.getReviews().size());

        repo.addReviewForCourse(1001L, new Review("4", "Very Good!"));
        Course updated = repo.findById(1001L);
        assertEquals(3, updated.getReviews().size());
    }

    @Test
    public void testCourseOfReview() {
        Review reivew = em.find(Review.class, 1000l);
        assertNotNull(reivew);
        assertEquals(1000l, reivew.getCourse().getId()); // ManyToOne going with EAGER fetch
    }

}
