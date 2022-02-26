package rdguez.eduardo.upax.service;

import rdguez.eduardo.upax.model.response.EmployeePaymentResponse;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursRequest;

public interface EmployeePaymentService {

  EmployeePaymentResponse findEmployeePaymentByDates(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  );

}
