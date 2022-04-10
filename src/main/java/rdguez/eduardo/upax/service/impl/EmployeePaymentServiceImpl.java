package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.dto.EmployeePaymentDto;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.response.EmployeePaymentResponse;
import rdguez.eduardo.upax.service.EmployeePaymentService;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.service.EmployeeWorkedHoursService;
import rdguez.eduardo.upax.util.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

  public BigDecimal calculatePaymentBySalaryAndWorkingDays(double salary, int workingDays) {
    int lengthOfMonth = DateUtil.currentLocalDate().lengthOfMonth();
    BigDecimal salaryByDay = BigDecimal.valueOf(salary / lengthOfMonth);
    BigDecimal payment = salaryByDay.multiply(BigDecimal.valueOf(workingDays));

    return payment.setScale(2, RoundingMode.HALF_EVEN);
  }

  public EmployeePaymentResponse employeePayment(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest,
    Employee employee
  ) {
    List<EmployeeWorkedHours> workedHoursList = employeeWorkedHoursService.findWorkedHoursByDates(
      employeeWorkedHoursRequest
    );

    double salary = employee.getJob().getSalary();
    int workingDays = workedHoursList.size();
    BigDecimal payment = calculatePaymentBySalaryAndWorkingDays(salary, workingDays);

    return EmployeePaymentDto.toResponse(payment);
  }

}
