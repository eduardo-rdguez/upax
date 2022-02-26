package rdguez.eduardo.upax.dto;

import rdguez.eduardo.upax.model.response.EmployeePaymentResponse;

public class EmployeePaymentDto {

  public static EmployeePaymentResponse toResponse(double payment) {
    return EmployeePaymentResponse.builder()
      .payment(payment)
      .success(true)
      .build();
  }

}
