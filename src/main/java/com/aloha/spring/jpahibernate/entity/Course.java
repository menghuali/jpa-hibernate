package com.aloha.spring.jpahibernate.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Entity
@Table(name = "CourseDetails")
@NamedQueries(value = {
    @NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "query_find_course_jpa_x_steps", query = "SELECT c FROM Course c WHERE name LIKE 'JPA in % Steps'")
})
public class Course {

    @Getter
    @GeneratedValue
    @Id
    private Long id;

    @Setter
    @Getter
    @Column(name = "fullname", nullable = false)
    private String name;

    @Getter
    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @Getter
    @CreationTimestamp
    private LocalDateTime createdTime;

    public Course(String name) {
        this.name = name;
    }

}
