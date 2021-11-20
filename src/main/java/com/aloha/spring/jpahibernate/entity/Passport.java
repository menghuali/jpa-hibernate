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
@ToString(exclude = "student")
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

    /**
     * Sample of Bidirectional One-to-One mapping. Set 'mappedBy' in OneToOne
     * annotation to avoid duplicates IDs on passport side. Student owns the
     * relationship.
     */
    @Setter
    @Getter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    private Student student;

    public Passport(String number) {
        this.number = number;
    }

}
