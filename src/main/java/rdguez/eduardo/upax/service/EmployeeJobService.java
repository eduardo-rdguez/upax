package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.model.request.EmployeeRequest;
import rdguez.eduardo.upax.model.response.EmployeeResponse;

public interface EmployeeJobService {

  EmployeeResponse findEmployeesByJob(EmployeeRequest employeeRequest);

}
