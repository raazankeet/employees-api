package com.raazankeet.employeesapi.repository;

import com.raazankeet.employeesapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
