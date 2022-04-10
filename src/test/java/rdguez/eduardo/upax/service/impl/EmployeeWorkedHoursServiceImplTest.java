package rdguez.eduardo.upax.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.response.EmployeeStatusResponse;
import rdguez.eduardo.upax.model.response.EmployeeWorkedHoursResponse;
import rdguez.eduardo.upax.repository.EmployeeWorkedHoursRepository;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.util.DateUtil;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class EmployeeWorkedHoursServiceImplTest {

  @InjectMocks
  EmployeeWorkedHoursServiceImpl employeeWorkedHoursService;

  @Mock
  EmployeeService employeeService;

  @Mock
  EmployeeWorkedHoursRepository employeeWorkedHoursRepository;

  @Test
  void addEmployeeWorkedHours() {
    Mockito
      .when(
        employeeWorkedHoursRepository.findOneByEmployee_IdAndWorkedDate(
          Mockito.anyLong(), Mockito.any()
        )
      )
      .thenReturn(Optional.empty());

    Mockito
      .when(employeeService.findEmployeeById(Mockito.anyLong()))
      .thenReturn(Optional.of(new Employee()));

    Mockito
      .when(
        employeeWorkedHoursRepository.save(
          Mockito.any(EmployeeWorkedHours.class)
        )
      )
      .thenReturn(this.mockEmployeeWorkedHours());

    EmployeeStatusResponse employeeStatusResponse = employeeWorkedHoursService.addEmployeeWorkedHours(
      this.mockEmployeeWorkedHoursRequest()
    );

    assert employeeStatusResponse.getSuccess();
    assert employeeStatusResponse.getId() == 1L;
  }

  @Test
  void findWorkedHoursByDates() {
    Mockito
      .when(
        employeeWorkedHoursRepository.findAllByEmployee_IdAndWorkedDateBetween(
          Mockito.anyLong(),
          Mockito.any(),
          Mockito.any()
        )
      )
      .thenReturn(this.mockEmployeeWorkedHoursList());

    List<EmployeeWorkedHours> employeeWorkedHoursList =
      employeeWorkedHoursService.findWorkedHoursByDates(
        this.mockEmployeeWorkedHoursRequest()
      );

    assert !employeeWorkedHoursList.isEmpty();
    assert employeeWorkedHoursList.size() == 1;
    assert employeeWorkedHoursList.get(0).getId() == 1L;
  }

  @Test
  void findTotalWorkedHoursByDates() {
    Mockito
      .when(
        employeeWorkedHoursRepository.findAllByEmployee_IdAndWorkedDateBetween(
          Mockito.anyLong(),
          Mockito.any(),
          Mockito.any()
        )
      )
      .thenReturn(this.mockEmployeeWorkedHoursList());

    EmployeeWorkedHoursResponse employeeWorkedHoursResponse =
      employeeWorkedHoursService.findTotalWorkedHoursByDates(
        this.mockEmployeeWorkedHoursRequest()
      );

    assert employeeWorkedHoursResponse.getSuccess();
    assert employeeWorkedHoursResponse.getTotalWorkedHours() == 40;
  }

  private EmployeeWorkedHours mockEmployeeWorkedHours() {
    EmployeeWorkedHours employeeWorkedHours = new EmployeeWorkedHours();
    employeeWorkedHours.setId(1L);
    employeeWorkedHours.setWorkedHours(40);

    return employeeWorkedHours;
  }

  private EmployeeWorkedHoursRequest mockEmployeeWorkedHoursRequest() {
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest = new EmployeeWorkedHoursRequest();
    employeeWorkedHoursRequest.setEmployeeId(1L);
    employeeWorkedHoursRequest.setWorkedHours(20);
    employeeWorkedHoursRequest.setWorkedDate(DateUtil.stringToDate("2022-02-18"));

    return employeeWorkedHoursRequest;
  }

  private List<EmployeeWorkedHours> mockEmployeeWorkedHoursList() {
    return List.of(
      this.mockEmployeeWorkedHours()
    );
  }
}