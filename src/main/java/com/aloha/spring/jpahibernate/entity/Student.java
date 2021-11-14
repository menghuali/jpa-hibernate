package com.aloha.spring.jpahibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
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

    /**
     * Sample of One-to-One mapping. Eager Fetch is the default fetching option,
     * where the referenced object will be fetched automatically.
     * 
     * To avoid automatic fetching referenced objects, use Lazy Fetch.
     */
    @Setter
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

}
