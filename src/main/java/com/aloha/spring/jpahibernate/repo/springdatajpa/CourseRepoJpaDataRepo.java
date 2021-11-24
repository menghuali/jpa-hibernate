package com.aloha.spring.jpahibernate.repo.springdatajpa;

import java.util.List;

import com.aloha.spring.jpahibernate.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "courses")
@Repository
public interface CourseRepoJpaDataRepo extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);

    Long countByName(String name);

    List<Course> deleteByName(String name);

    @Query("SELECT c FROM Course c WHERE c.name LIKE '%100 Steps'")
    List<Course> findCourseWith100StepsJPQL();

    @Query(value = "SELECT c FROM Course c WHERE c.name LIKE '%100 Steps'", nativeQuery = true)
    List<Course> findCourseWith100StepsNativeQuery();

    @Query(name = "query_find_course_spider")
    List<Course> findSpiderCoursesNamedQuery();

    @Query("SELECT c FROM Course c WHERE c.name LIKE ?1")
    List<Course> findCourseByNameJPQLIndexParam(String name);

    @Query("SELECT c FROM Course c WHERE c.name LIKE :name")
    List<Course> findCourseByNameJPQLNamedParam(@Param("name") String name);
}
