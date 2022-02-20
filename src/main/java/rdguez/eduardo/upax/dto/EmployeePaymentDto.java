package rdguez.eduardo.upax.dto;

import rdguez.eduardo.upax.model.EmployeePaymentResponse;

public class EmployeePaymentDto {

  public static EmployeePaymentResponse toResponse(Double payment) {
    return EmployeePaymentResponse.builder()
      .payment(payment)
      .success(true)
      .build();
  }

}
