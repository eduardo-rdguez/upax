package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeResponse;

public interface EmployeeJobService {

  EmployeeResponse findEmployeesByJob(EmployeeRequest employeeRequest);

}
