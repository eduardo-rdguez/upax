package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.model.EmployeeRequest;

import java.util.List;

public interface EmployeeJobService {

  List<Employee> findEmployeesByJob(EmployeeRequest employeeRequest);

}
