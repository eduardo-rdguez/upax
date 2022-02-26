package rdguez.eduardo.upax.dto;

import lombok.extern.slf4j.Slf4j;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursResponse;

@Slf4j
public class EmployeeWorkedHoursDto {

  public static EmployeeResponse toResponse(EmployeeWorkedHours employeeWorkedHours) {
    return EmployeeResponse.builder()
      .id(employeeWorkedHours.getId())
      .success(true)
      .build();
  }

  public static EmployeeWorkedHoursResponse toResponse(int totalWorkedHours) {
    return EmployeeWorkedHoursResponse.builder()
      .totalWorkedHours(totalWorkedHours)
      .success(true)
      .build();
  }

  public static EmployeeWorkedHours toEntity(EmployeeWorkedHoursRequest employeeWorkedHoursRequest, Employee employee) {
    EmployeeWorkedHours employeeWorkedHours = new EmployeeWorkedHours();
    int workedHours = employeeWorkedHoursRequest.getWorkedHours();

    employeeWorkedHours.setWorkedHours(workedHours);
    employeeWorkedHours.setWorkedDate(employeeWorkedHoursRequest.getWorkedDate());
    employeeWorkedHours.setEmployee(employee);

    return employeeWorkedHours;
  }

}
