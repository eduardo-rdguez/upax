package rdguez.eduardo.upax.dto;

import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.Gender;
import rdguez.eduardo.upax.domain.Job;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeResponse;

public class EmployeeDto {

  public static EmployeeResponse toResponse(Employee employee) {
    return EmployeeResponse.builder()
      .id(employee.getId())
      .success(true)
      .build();
  }

  public static Employee toEntity(EmployeeRequest employeeRequest, Gender gender, Job job) {
    Employee employee = new Employee();
    employee.setName(employeeRequest.getName());
    employee.setLastName(employeeRequest.getLastName());
    employee.setBirthdate(employeeRequest.getBirthdate());
    employee.setGender(gender);
    employee.setJob(job);

    return employee;
  }

}
