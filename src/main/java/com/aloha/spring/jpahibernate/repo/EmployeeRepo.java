package com.aloha.spring.jpahibernate.repo;

import java.util.List;

import javax.persistence.EntityManager;

import com.aloha.spring.jpahibernate.entity.Employee;
import com.aloha.spring.jpahibernate.entity.FullTimeEmployee;
import com.aloha.spring.jpahibernate.entity.PartTimeEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmployeeRepo {

    @Autowired
    private EntityManager em;

    public void save(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> findAllEmployee() {
        // Notice: JPA is case sensitive. Entity name must be exactly same as the class
        // name. In this case, it must be 'Employee'; 'EMPLOYEE' and 'employee' won't
        // work.
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public List<PartTimeEmployee> findAllPartTimeEmployee() {
        return em.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> findAllFullTimeEmployee() {
        return em.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

}
