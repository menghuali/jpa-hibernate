package com.aloha.spring.jpahibernate.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass // Other optiona to map inheritance. With this option, each subclass is a table, 
                  // and the superclass is not mapped to any table.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
// @Entity
// @Inheritance(strategy = InheritanceType.JOINED)
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // One table per subclass
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Default inheritance strategy. Fields of supper/sub classes are
//                                                       // saved in one table
// @DiscriminatorColumn(name = "empType") // Name of the column discriminates subclasses
public abstract class Employee {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id_seq")
    @SequenceGenerator(name = "emp_id_seq", sequenceName = "EMP_ID_SEQ", initialValue = 1000, allocationSize = 1)
    private Long id;

    @Setter
    @Getter
    private String name;

    public Employee(String name) {
        this.name = name;
    }

}
