package rdguez.eduardo.upax.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.model.request.EmployeeRequest;
import rdguez.eduardo.upax.model.response.EmployeeResponse;
import rdguez.eduardo.upax.repository.EmployeeRepository;

import java.util.List;

@SpringBootTest
class EmployeeJobServiceImplTest {

  @InjectMocks
  EmployeeJobServiceImpl employeeService;

  @Mock
  EmployeeRepository employeeRepository;

  @Test
  void findEmployeesByJob() {
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setJobId(1L);

    Mockito
      .when(employeeRepository.findAllByJob_Id(Mockito.anyLong()))
      .thenReturn(this.mockEmployeeList());

    EmployeeResponse employeeResponse = employeeService.findEmployeesByJob(employeeRequest);

    assert employeeResponse.getSuccess();
    assert !employeeResponse.getEmployees().isEmpty();
    assert employeeResponse.getEmployees().size() == 1;
  }

  private List<Employee> mockEmployeeList() {
    return List.of(
      new Employee()
    );
  }
}
