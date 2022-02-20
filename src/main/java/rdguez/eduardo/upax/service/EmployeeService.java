package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeResponse;

import java.util.Optional;

public interface EmployeeService {

  Optional<Employee> findEmployeeById(Long id);

  EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

}
