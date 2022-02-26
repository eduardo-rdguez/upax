package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeStatusResponse;

import java.util.Optional;

public interface EmployeeService {

  Optional<Employee> findEmployeeById(Long id);

  EmployeeStatusResponse addEmployee(EmployeeRequest employeeRequest);

}
