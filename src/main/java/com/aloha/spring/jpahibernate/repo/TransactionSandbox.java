package com.aloha.spring.jpahibernate.repo;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Passport;
import com.aloha.spring.jpahibernate.entity.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class TransactionSandbox {

    @Autowired
    private EntityManager em;

    /**
     * Sample of multi-operation transaction. The change will be committed at the
     * end opf the method
     */
    public void multiOps1() {
        Student student = em.find(Student.class, 1002L);
        Passport passport = student.getPassport();
        passport.setNumber(passport.getNumber() + "1");
        student.setName(student.getName() + "1");
    }

    /**
     * Sample of multi-operation transaction. The change will be committed at the
     * end opf the method
     */
    public void multiOps2() {
        Student student = em.find(Student.class, 1002L);
        Passport passport = student.getPassport();
        passport.setNumber(passport.getNumber() + "2");
        em.flush(); // Flush the changes to DB. But the actual effect depends on transaction
                    // isolation settings. In other words, the changes might not be visible to
                    // others
        student.setName(student.getName() + "2");
        throw new RuntimeException("intended exception"); // This will rollback all the changes, include the ones before flush.
    }
}
