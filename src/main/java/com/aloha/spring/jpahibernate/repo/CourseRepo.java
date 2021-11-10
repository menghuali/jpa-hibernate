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

    /**
     * Sample of Entity Manager
     * 
     * @return the record from DBF
     */
    public Course playWithEntityManager() {
        Course course = new Course("Play with Entity Manager - 1");
        em.persist(course);
        course.setName("[Update-1]Play with Entity Manager - 1"); // EM tracks this operation. It will be saved to DB //
                                                                  // even if em.persist isn't called explicitly
        em.flush(); // flush the entities to DB
        em.detach(course); // Detach entity from EM so that the subsequent operations won't go to DB.
        course.setName("[Update-2]Play with Entity Manager - 1");
        // em.clear(); // option-B Detach all entities from EM.
        return findById(course.getId());
    }

    /**
     * Sample of Entity Manager - Refresh
     * 
     * @return the record from DB
     */
    public Course playWithEntityManager_Refresh() {
        Course course = new Course("Play with Entity Manager - 2");
        em.persist(course);
        em.flush();
        course.setName("[Updated] Play with Entity Manager - 2");
        em.refresh(course); // It refreshes entity with DB data. CAUTION: Make sure when you call this
                            // method the record is in DB; otherwise exception will occurr!
        return course;
    }

}
