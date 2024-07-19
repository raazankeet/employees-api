package com.raazankeet.employeesapi.service;

import com.raazankeet.employeesapi.exception.EmployeeNotFoundException;
import com.raazankeet.employeesapi.model.Employee;
import com.raazankeet.employeesapi.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        employee.setName(employeeDetails.getName());
        employee.setPosition(employeeDetails.getPosition());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        employee.setCity(employeeDetails.getCity());
        employee.setEmail(employeeDetails.getEmail());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    @Transactional
    public void loadData() {
        // Clear all data
        employeeRepository.deleteAll();

        // Reset auto-increment sequence (works for H2, MySQL, PostgreSQL, etc.)
        entityManager.createNativeQuery("TRUNCATE TABLE employee").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE employee ALTER COLUMN id RESTART WITH 1").executeUpdate();

        // Initial data load
        String csvData = "1,Ankit Raj,Senior Architect,IT,60000,New Delhi,raazankeet@gmail.com\n" +
                "2,Sugata Saha,Senior Architect,IT,75000,Kolkata,sugata.saha123@wipro.com\n" +
                "3,Hemant Waradkar,General Manager,IT,50000,Pune,hemant_waradkar@abc.com\n" +
                "4,Santanu Bhaduri ,Architect,IT,70000,Kolkata,santanu.bhaduri@example.com\n" +
                "5,Purushottam Joshi,Principal Architect,IT,80000,Bengaluru,joshi_puru@pqr.com\n" +
                "6,Aarohi Sharma,Consultant,Consulting,65000,Hyderabad,aarohi.kumar@example.com\n" +
                "7,Ayaan Mehta,Designer,Marketing,55000,Pune,ayaan.mehta@example.com\n" +
                "8,Aryan Reddy,Director,Operations,90000,Ahmedabad,aryan.reddy@example.com\n" +
                "9,Arjun Rao,Architect,Engineering,95000,Jaipur,arjun.rao@example.com\n" +
                "10,Diya Iyer,Administrator,Admin,60000,Surat,diya.iyer@example.com";
        String[] lines = csvData.split("\n");
        for (String line : lines) {
            String[] attributes = line.split(",");
            Employee employee = new Employee();
            employee.setId(Long.parseLong(attributes[0]));
            employee.setName(attributes[1]);
            employee.setPosition(attributes[2]);
            employee.setDepartment(attributes[3]);
            employee.setSalary(Double.parseDouble(attributes[4]));
            employee.setCity(attributes[5]);
            employee.setEmail(attributes[6]);
            employeeRepository.save(employee);
        }
    }
}
