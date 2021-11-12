package com.aloha.spring.jpahibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
public class Student {

    @Getter
    @Id
    @GeneratedValue(generator = "student_id_seq")
    @SequenceGenerator(name = "student_id_seq", sequenceName = "STUDENT_ID_SEQ", initialValue = 1000, allocationSize = 1)
    private Long id;

    @Setter
    @Getter
    @Column(name = "fullname", nullable = false)
    private String name;

}
