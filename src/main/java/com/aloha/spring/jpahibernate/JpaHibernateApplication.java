package com.aloha.spring.jpahibernate;

import java.math.BigDecimal;

import com.aloha.spring.jpahibernate.entity.FullTimeEmployee;
import com.aloha.spring.jpahibernate.entity.PartTimeEmployee;
import com.aloha.spring.jpahibernate.entity.Review;
import com.aloha.spring.jpahibernate.repo.CourseRepo;
import com.aloha.spring.jpahibernate.repo.EmployeeRepo;
import com.aloha.spring.jpahibernate.repo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("all")
@Slf4j
@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private StudentRepo studentRepo;


	@Autowired EmployeeRepo empRepo;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		courseRepo.addReviewForCourse(1000l, new Review("4", "Very Good!"), new Review("5", "Awsome!"));
		log.info("End");
		// studentRepo.insertStudentAndCourse(new Student("Aloha"), new Course("Spring JPA"));
		empRepo.save(new FullTimeEmployee("Peter Parker", BigDecimal.valueOf(115000.0)));
		empRepo.save(new PartTimeEmployee("Bruce Wayne", BigDecimal.valueOf(55.0)));
		// log.info("Employee: {}", empRepo.findAllEmployee());
		log.info("Part time employee: {}", empRepo.findAllPartTimeEmployee());
		log.info("Full time employee: {}", empRepo.findAllFullTimeEmployee());
	}

}
