package com.raazankeet.employeesapi.controller;

import com.raazankeet.employeesapi.exception.EmployeeNotFoundException;
import com.raazankeet.employeesapi.model.Employee;
import com.raazankeet.employeesapi.response.StatusResponse;
import com.raazankeet.employeesapi.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved employee"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    @Operation(summary = "Create a new employee", description = "Add a new employee to the system.")
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @Operation(summary = "Update an existing employee", description = "Update the details of an existing employee.")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Operation(summary = "Delete an employee", description = "Remove an employee from the system.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all employees", description = "Remove all employees from the system.")
    @DeleteMapping
    public ResponseEntity<StatusResponse> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        StatusResponse response = new StatusResponse("success", "All employees deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Reload employee data", description = "Reload the employee data from the initial set.")
    @PostMapping("/reload")
    public ResponseEntity<StatusResponse> reloadData() {
        employeeService.loadData();
        StatusResponse response = new StatusResponse("success", "Data reloaded successfully.");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
