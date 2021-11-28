package com.aloha.spring.jpahibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Entity
@Table(name = "Course")
@NamedQueries(value = { @NamedQuery(name = "query_get_all_courses", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "query_find_course_spider", query = "SELECT c FROM Course c WHERE name LIKE '%Spider%'") })
@Cacheable
@SQLDelete(sql = "UPDATE COURSE SET IS_DELETED = true WHERE ID = ?") // SQL for Soft Delete
@Where(clause = "IS_DELETED = false") // Do not return an entity if it is Soft Deleted
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

    @JsonIgnore
    @ManyToMany(mappedBy = "courses") // Make Student the owner of relationship
    @Getter
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "course"/* , fetch = FetchType.EAGER */) // default fetch mode is LAZY
    @Getter
    private List<Review> reviews = new ArrayList<>();

    @Getter
    @Column(columnDefinition = "boolean default false") // Default value for SQL
    private Boolean isDeleted = false; // Default value for enity (Both are importnat)

    /**
     * Set isDeleted to true when an entity is about to be removed
     */
    @PreRemove
    private void preRemove() {
        isDeleted = true;
    }

    public Course(String name) {
        this.name = name;
    }

    public boolean addReview(Review review) {
        return reviews.add(review);
    }

    public boolean removeReview(Review review) {
        return reviews.remove(review);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

}
