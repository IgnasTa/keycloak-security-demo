package dev.ignas.security.demo.app.service;

import dev.ignas.security.demo.app.entity.Employee;
import dev.ignas.security.demo.app.exception.WrongDataFormat;
import dev.ignas.security.demo.app.storage.EmployeeStorage;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeStorage employeeStorage;

    public List<Employee> getAll(){
        return employeeStorage.findAll();
    }

    public List<Employee> filterEmployeesBySalary(Double amount){
        var employees = getAll();
        try {
            return employees.stream()
                    .filter(e -> e.getSalary() != null)
                    .filter(e -> e.getSalary() > amount)
                    .toList();
        } catch (NullPointerException e){
            throw new WrongDataFormat(e.getMessage());
        }
    }

    public void deleteById(Integer id){
        employeeStorage.deleteById(id);
    }

}
