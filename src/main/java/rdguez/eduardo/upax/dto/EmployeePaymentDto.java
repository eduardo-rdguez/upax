package rdguez.eduardo.upax.dto;

import rdguez.eduardo.upax.model.response.EmployeePaymentResponse;

import java.math.BigDecimal;

public class EmployeePaymentDto {

  public static EmployeePaymentResponse toResponse(BigDecimal payment) {
    return EmployeePaymentResponse.builder()
      .payment(payment)
      .success(true)
      .build();
  }

}
