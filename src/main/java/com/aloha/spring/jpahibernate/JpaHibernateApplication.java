package com.aloha.spring.jpahibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("all")
@Slf4j
@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
