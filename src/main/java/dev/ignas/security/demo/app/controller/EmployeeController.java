package dev.ignas.security.demo.app.controller;

import dev.ignas.security.demo.app.entity.Employee;
import dev.ignas.security.demo.app.service.EmployeeService;
import dev.ignas.security.demo.app.storage.EmployeeStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("employee")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "employee", params = {"salary"})
    public ResponseEntity<?> getAllEmployeesFiltered(@RequestParam(name = "salary") Double salary){
        List<Employee> employees = employeeService.filterEmployeesBySalary(salary);
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id){
        employeeService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
