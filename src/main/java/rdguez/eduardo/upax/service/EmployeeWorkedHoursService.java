package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursResponse;

import java.util.List;

public interface EmployeeWorkedHoursService {

  EmployeeResponse addEmployeeWorkedHours(EmployeeWorkedHoursRequest employeeWorkedHours);

  List<EmployeeWorkedHours> findWorkedHoursByDates(EmployeeWorkedHoursRequest employeeWorkedHoursRequest);

  EmployeeWorkedHoursResponse findTotalWorkedHoursByDates(EmployeeWorkedHoursRequest employeeWorkedHoursRequest);

}
