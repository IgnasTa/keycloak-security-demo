package dev.ignas.security.demo.app.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class AppController {

    private final JdbcTemplate jdbcTemplate;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("employee")
    public ResponseEntity<?> getAllEmployees(){
        String query = "SELECT * FROM employees;";
        return ResponseEntity.ok(jdbcTemplate.queryForList(query));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("employee/{person_id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable String person_id){
        String query = String.format("DELETE FROM employees WHERE id = %s",person_id);
        jdbcTemplate.execute(query);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
