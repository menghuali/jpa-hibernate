package com.aloha.spring.jpahibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "query_find_course_spider", query = "SELECT c FROM Course c WHERE name LIKE '%Spider%'") })
public class Course {

    @Getter
    @Id
    @GeneratedValue(generator = "course_id_seq")
    @SequenceGenerator(name = "course_id_seq", sequenceName = "COURSE_ID_SEQ", initialValue = 1000, allocationSize = 1)
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

    @OneToMany(mappedBy = "course"/* , fetch = FetchType.EAGER */) // default fetch mode is LAZY
    @Getter
    private List<Review> reviews = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public boolean addReview(Review review) {
        return reviews.add(review);
    }

    public boolean removeReview(Review review) {
        return reviews.remove(review);
    }

}
