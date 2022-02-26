package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursResponse;
import rdguez.eduardo.upax.model.response.EmployeeStatusResponse;

import java.util.List;

public interface EmployeeWorkedHoursService {

  EmployeeStatusResponse addEmployeeWorkedHours(EmployeeWorkedHoursRequest employeeWorkedHours);

  List<EmployeeWorkedHours> findWorkedHoursByDates(EmployeeWorkedHoursRequest employeeWorkedHoursRequest);

  EmployeeWorkedHoursResponse findTotalWorkedHoursByDates(EmployeeWorkedHoursRequest employeeWorkedHoursRequest);

}
