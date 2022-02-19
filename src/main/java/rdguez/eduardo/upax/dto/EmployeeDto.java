package rdguez.eduardo.upax.dto;

import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.Gender;
import rdguez.eduardo.upax.domain.Job;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeResponse;

import java.util.Date;

public class EmployeeDto {

  public static EmployeeResponse toResponse(Employee employee) {
    return EmployeeResponse.builder()
      .id(employee.getId())
      .success(true)
      .build();
  }

  public static Employee toEntity(EmployeeRequest employeeRequest, Gender gender, Job job) {
    String name = employeeRequest.getName();
    String lastName = employeeRequest.getLastName();
    Date birthdate = employeeRequest.getBirthdate();

    Employee employee = new Employee();
    employee.setName(name);
    employee.setLastName(lastName);
    employee.setBirthdate(birthdate);
    employee.setGender(gender);
    employee.setJob(job);

    return employee;
  }

}
