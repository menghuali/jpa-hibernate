package com.aloha.spring.jpahibernate.repo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aloha.spring.jpahibernate.entity.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class NPlusOneProblemTests {

    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    public void nPlusOneProblem() {
        em.createQuery("SELECT c FROM Course c", Course.class).getResultStream().forEach(c -> {
            assertNotNull(c);
            log.info("Course={}, Students={}", c, c.getStudents());
        });
    }

    @Transactional
    @Test
    public void nPlusOneProblemSolution_EntityGraph() {
        EntityGraph<Course> graph = em.createEntityGraph(Course.class);
        graph.addSubgraph("students");
        em.createQuery("SELECT c FROM Course c", Course.class).setHint("javax.persistence.loadgraph", graph)
                .getResultStream().forEach(c -> {
                    assertNotNull(c);
                    log.info("Course={}, Students={}", c, c.getStudents());
                });
    }

    @Transactional
    @Test
    public void nPlusOneProblemSolution_JoinFetch() {
        em.createQuery("SELECT c FROM Course c JOIN FETCH c.students", Course.class)
                .getResultStream().forEach(c -> {
                    assertNotNull(c);
                    log.info("Course={}, Students={}", c, c.getStudents());
                });
    }

}
