package dev.ignas.security.demo.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/")
@RestController
public class AppController {

    @GetMapping("")
    public ResponseEntity<?> welcome(){
        return ResponseEntity.ok("Welcome!");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("employee")
    public ResponseEntity<?> getAllEmployees(){
        List<Map<String, Object>> employees = new ArrayList<>();

        Map<String, Object> employee1 = new HashMap<>();
        employee1.put("ID", 101);
        employee1.put("Name", "John");
        employee1.put("Last Name", "Doe");
        employee1.put("Salary", 75000.0);

        Map<String, Object> employee2 = new HashMap<>();
        employee2.put("ID", 102);
        employee2.put("Name", "Jane");
        employee2.put("Last Name", "Smith");
        employee2.put("Salary", 82000.0);

        Map<String, Object> employee3 = new HashMap<>();
        employee3.put("ID", 103);
        employee3.put("Name", "Alice");
        employee3.put("Last Name", "Johnson");
        employee3.put("Salary", 90000.0);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        return ResponseEntity.ok(employees);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("employee/{person_id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String person_id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
