package rdguez.eduardo.upax.dto;

import lombok.extern.slf4j.Slf4j;
import rdguez.eduardo.upax.constant.Constants;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;

@Slf4j
public class EmployeeWorkedHoursDto {

  public static EmployeeResponse toResponse(EmployeeWorkedHours employeeWorkedHours) {
    return EmployeeResponse.builder()
      .id(employeeWorkedHours.getId())
      .success(true)
      .build();
  }

  public static EmployeeWorkedHours toEntity(EmployeeWorkedHoursRequest employeeWorkedHoursRequest, Employee employee) {
    EmployeeWorkedHours employeeWorkedHours = new EmployeeWorkedHours();
    Integer workedHours = getWorkedHours(employeeWorkedHoursRequest.getWorkedHours());

    employeeWorkedHours.setWorkedHours(workedHours);
    employeeWorkedHours.setWorkedDate(employeeWorkedHoursRequest.getWorkedDate());
    employeeWorkedHours.setEmployee(employee);

    return employeeWorkedHours;
  }

  public static Integer getWorkedHours(Integer workedHours) {
    return workedHours <= Constants.HOURS_ALLOWED_WORKED ? workedHours : Constants.HOURS_ALLOWED_WORKED;
  }

}
