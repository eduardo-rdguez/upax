package rdguez.eduardo.upax.dto;

import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.Gender;
import rdguez.eduardo.upax.domain.Job;
import rdguez.eduardo.upax.model.request.EmployeeRequest;
import rdguez.eduardo.upax.model.response.EmployeeResponse;
import rdguez.eduardo.upax.model.response.EmployeeStatusResponse;

import java.util.List;

public class EmployeeDto {

  public static EmployeeStatusResponse toResponse(Employee employee) {
    return EmployeeStatusResponse.builder()
      .id(employee.getId())
      .success(true)
      .build();
  }

  public static EmployeeResponse toResponse(List<Employee> employees) {
    return EmployeeResponse.builder()
      .employees(employees)
      .success(!employees.isEmpty())
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
