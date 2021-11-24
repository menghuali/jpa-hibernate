package com.aloha.spring.jpahibernate.repo.springdatajpa;

import com.aloha.spring.jpahibernate.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepoJpaDataRepo extends JpaRepository<Course, Long> {
    
}
