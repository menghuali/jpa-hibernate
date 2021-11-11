package com.aloha.spring.jpahibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Entity
@Table(name = "CourseDetails")
public class Course {

    @Getter
    @GeneratedValue
    @Id
    private Long id;

    @Setter
    @Getter
    private String name;

    public Course(String name) {
        this.name = name;
    }

}
