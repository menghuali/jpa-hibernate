package com.aloha.spring.jpahibernate;

import com.aloha.spring.jpahibernate.repo.TransactionSandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {
	@Autowired
	private TransactionSandbox txSandbox;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		txSandbox.multiOps1();
		try {
			txSandbox.multiOps2();
		} catch (Exception e) {
		}
		log.info("End");
	}

}
