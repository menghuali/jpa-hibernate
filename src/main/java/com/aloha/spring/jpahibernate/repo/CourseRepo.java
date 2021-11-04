package com.aloha.spring.jpahibernate.repo;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.aloha.spring.jpahibernate.entity.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class CourseRepo {

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {
        return em.merge(course);
    }

    public Course deleteById(Long id) {
        Course course = findById(id);
        if (course != null)
            em.remove(course);
        return course;
    }

}
