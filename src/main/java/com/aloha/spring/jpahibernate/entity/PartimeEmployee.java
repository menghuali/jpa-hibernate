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
public class PartimeEmployee extends Employee {

    @Setter
    @Getter
    private BigDecimal hourlyWage;

    public PartimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

}
