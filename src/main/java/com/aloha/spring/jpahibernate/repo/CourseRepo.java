package com.aloha.spring.jpahibernate.repo;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CourseRepo {

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null)
            em.persist(course);
        else
            em.merge(course);
        return course;
    }

    public Course deleteById(Long id) {
        Course course = findById(id);
        if (course != null)
            em.remove(course);
        return course;
    }

}
