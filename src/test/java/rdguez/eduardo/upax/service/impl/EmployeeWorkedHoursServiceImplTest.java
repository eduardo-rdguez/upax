package rdguez.eduardo.upax.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursResponse;
import rdguez.eduardo.upax.repository.EmployeeWorkedHoursRepository;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
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

  @Mock
  List<EmployeeWorkedHours> employeeWorkedHoursMockList = new ArrayList<>();

  @Test
  void addEmployeeWorkedHours() {
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest = new EmployeeWorkedHoursRequest();
    employeeWorkedHoursRequest.setWorkedDate(new Date());

    Mockito
      .when(employeeService.findEmployeeById(Mockito.anyLong()))
      .thenReturn(Optional.of(new Employee()));

    EmployeeResponse employeeResponse = employeeWorkedHoursService.addEmployeeWorkedHours(
      employeeWorkedHoursRequest
    );

    assert !employeeResponse.getSuccess();
    assert employeeResponse.getId() == null;
  }

  @Test
  void addEmployeeWorkedHours2() {
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest = new EmployeeWorkedHoursRequest();
    employeeWorkedHoursRequest.setWorkedDate(DateUtil.stringToDate("2022-02-18"));

    Mockito
      .when(employeeService.findEmployeeById(Mockito.anyLong()))
      .thenReturn(Optional.of(new Employee()));

    Mockito
      .when(
        employeeWorkedHoursRepository.findOneByEmployee_IdAndWorkedDate(
          Mockito.anyLong(), Mockito.any()
        )
      )
      .thenReturn(Optional.of(new EmployeeWorkedHours()));

    EmployeeResponse employeeResponse = employeeWorkedHoursService.addEmployeeWorkedHours(
      employeeWorkedHoursRequest
    );

    assert !employeeResponse.getSuccess();
    assert employeeResponse.getId() == null;
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
      .thenReturn(employeeWorkedHoursMockList);

    List<EmployeeWorkedHours> employeeWorkedHoursList =
      employeeWorkedHoursService.findWorkedHoursByDates(
        new EmployeeWorkedHoursRequest()
      );

    assert employeeWorkedHoursList.isEmpty();
  }

  @Test
  void findTotalWorkedHoursByDates() {
    Mockito
      .when(
        employeeWorkedHoursService.findWorkedHoursByDates(
          Mockito.mock(EmployeeWorkedHoursRequest.class)
        )
      )
      .thenReturn(employeeWorkedHoursMockList);

    EmployeeWorkedHoursResponse employeeWorkedHoursResponse =
      employeeWorkedHoursService.findTotalWorkedHoursByDates(
        new EmployeeWorkedHoursRequest()
      );

    assert !employeeWorkedHoursResponse.getSuccess();
  }
}