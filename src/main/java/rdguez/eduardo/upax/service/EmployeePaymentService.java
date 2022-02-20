package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.model.EmployeePaymentResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;

public interface EmployeePaymentService {

  EmployeePaymentResponse findEmployeePaymentByDates(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  );

}
