package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.dto.EmployeePaymentDto;
import rdguez.eduardo.upax.model.EmployeePaymentResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.service.EmployeePaymentService;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.service.EmployeeWorkedHoursService;
import rdguez.eduardo.upax.util.DateUtil;
import rdguez.eduardo.upax.util.RoundUtil;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeePaymentServiceImpl implements EmployeePaymentService {

  @Autowired
  EmployeeWorkedHoursService employeeWorkedHoursService;

  @Autowired
  EmployeeService employeeService;

  @Override
  public EmployeePaymentResponse findEmployeePaymentByDates(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  ) {
    Long employeeId = employeeWorkedHoursRequest.getEmployeeId();
    Optional<Employee> employee = employeeService.findEmployeeById(employeeId);
    EmployeePaymentResponse employeePaymentResponse = EmployeePaymentResponse.builder().build();

    if (employee.isPresent()) {
      return employeePayment(employeeWorkedHoursRequest, employee.get());
    }
    return employeePaymentResponse;
  }

  public Double calculatePaymentBySalaryAndWorkingDays(Double salary, Integer workingDays) {
    int lengthOfMonth = DateUtil.currentLocalDate().lengthOfMonth();
    double salaryByDay = salary / lengthOfMonth;
    double payment = salaryByDay * workingDays;

    return RoundUtil.roundAvoid(payment, 2);
  }

  public EmployeePaymentResponse employeePayment(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest,
    Employee employee
  ) {
    List<EmployeeWorkedHours> employeeWorkedHoursList = employeeWorkedHoursService.findWorkedHoursByDates(
      employeeWorkedHoursRequest
    );

    Double salary = employee.getJob().getSalary();
    Integer workingDays = employeeWorkedHoursList.size();
    Double payment = calculatePaymentBySalaryAndWorkingDays(salary, workingDays);

    return EmployeePaymentDto.toResponse(payment);
  }

}
