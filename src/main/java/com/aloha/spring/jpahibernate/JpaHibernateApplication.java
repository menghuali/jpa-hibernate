package com.aloha.spring.jpahibernate;

import com.aloha.spring.jpahibernate.entity.Review;
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
		courseRepo.addReviewForCourse(1000l, new Review("4", "Very Good!"), new Review("5", "Awsome!"));
		log.info("End");
	}

}
