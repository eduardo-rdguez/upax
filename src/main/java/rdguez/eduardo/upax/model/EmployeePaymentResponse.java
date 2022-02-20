package rdguez.eduardo.upax.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
public class EmployeePaymentResponse {

  private Double payment;

  @Builder.Default
  private Boolean success = false;

}
