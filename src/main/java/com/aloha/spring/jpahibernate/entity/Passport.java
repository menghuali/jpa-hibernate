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
public class Passport {

    @Getter
    @Id
    @GeneratedValue(generator = "passport_id_seq")
    @SequenceGenerator(name = "passport_id_seq", sequenceName = "PASSPORT_ID_SEQ", initialValue = 1000, allocationSize = 1)
    private Long id;

    @Setter
    @Getter
    @Column(name = "passport_num", nullable = false)
    private String number;

}
