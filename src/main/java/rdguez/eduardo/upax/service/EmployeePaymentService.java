package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.response.EmployeePaymentResponse;

public interface EmployeePaymentService {

  EmployeePaymentResponse findEmployeePaymentByDates(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  );

}
