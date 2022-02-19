package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;

public interface EmployeeWorkedHoursService {

  EmployeeResponse addEmployeeWorkedHours(EmployeeWorkedHoursRequest employeeWorkedHours);

}
