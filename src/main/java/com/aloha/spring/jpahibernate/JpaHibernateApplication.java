package com.aloha.spring.jpahibernate;

import com.aloha.spring.jpahibernate.entity.Course;
import com.aloha.spring.jpahibernate.repo.CourseRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	@Autowired
	private CourseRepo courseRepo;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("FIND COURSE BY ID({}): {}", 1L, courseRepo.findById(1L));

		Course course = courseRepo.save(new Course("New Course"));
		log.info("SAVE COURSE: {}", course);
		log.info("DELETE COURSE BY ID({}): {}", course.getId(), courseRepo.deleteById(course.getId()));
		log.info("PLAY WITH ENTITY MANAGER");
		courseRepo.playWithEntityManager();
	}

}
