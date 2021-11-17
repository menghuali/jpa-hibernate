package com.aloha.spring.jpahibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(generator = "review_id_seq")
    @SequenceGenerator(name = "review_id_seq", sequenceName = "REVIEW_ID_SEQ", initialValue = 1000, allocationSize = 1)
    @Getter
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String rating;

    @Setter
    @Getter
    @Column(name = "review_desc")
    private String description;

    @Setter
    @Getter
    @ManyToOne
    private Course course;

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

}
