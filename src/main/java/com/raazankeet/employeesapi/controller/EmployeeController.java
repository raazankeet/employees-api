package com.raazankeet.employeesapi.controller;

import com.raazankeet.employeesapi.exception.EmployeeNotFoundException;
import com.raazankeet.employeesapi.model.Employee;
import com.raazankeet.employeesapi.response.StatusResponse;
import com.raazankeet.employeesapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("Fetching employee with id: {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Operation(summary = "Create a new employee", description = "Add a new employee to the system.")
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        logger.info("Creating new employee: {}", employee);
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing employee", description = "Update the details of an existing employee.")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        logger.info("Updating employee with id: {}", id);
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Operation(summary = "Delete an employee", description = "Remove an employee from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<StatusResponse> deleteEmployee(@PathVariable Long id) {
        logger.info("Deleting employee with id: {}", id);
        employeeService.deleteEmployee(id);
        StatusResponse response = new StatusResponse("success", "Employee deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete all employees", description = "Remove all employees from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted all employees")
    })
    @DeleteMapping
    public ResponseEntity<StatusResponse> deleteAllEmployees() {
        logger.info("Deleting all employees");
        employeeService.deleteAllEmployees();
        StatusResponse response = new StatusResponse("success", "All employees deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Reload employee data", description = "Reload the employee data from the initial set.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data reloaded successfully")
    })
    @PostMapping("/reload")
    public ResponseEntity<StatusResponse> reloadData() {
        logger.info("Reloading employee data");
        employeeService.loadData();
        StatusResponse response = new StatusResponse("success", "Data reloaded successfully.");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        logger.error("EmployeeNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
