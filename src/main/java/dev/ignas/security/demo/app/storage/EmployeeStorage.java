package dev.ignas.security.demo.app.storage;

import dev.ignas.security.demo.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeStorage extends JpaRepository<Employee, Integer>{
    Optional<Employee> findById(Integer id);
    List<Employee> findAll();
    void deleteById(Integer id);
}
