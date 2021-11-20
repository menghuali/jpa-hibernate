package com.aloha.spring.jpahibernate.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
public class FulltimeEmployee extends Employee {

    @Setter
    @Getter
    private BigDecimal salary;

    public FulltimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

}
