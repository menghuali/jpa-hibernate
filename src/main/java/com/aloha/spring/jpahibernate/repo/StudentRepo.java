package com.aloha.spring.jpahibernate.repo;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StudentRepo {

    @Autowired
    private EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            if (student.getPassport() != null) // Persist passport first if any. Otherwise, you will get JPA exception
                                               // about transiet object reference.
                em.persist(student.getPassport());
            em.persist(student);
        } else {
            if (student.getPassport() != null)
                em.merge(student.getPassport());
            em.merge(student);
        }
        return student;
    }

    public Student deleteById(Long id) {
        Student student = findById(id);
        if (student != null)
            em.remove(student);
        return student;
    }

}
