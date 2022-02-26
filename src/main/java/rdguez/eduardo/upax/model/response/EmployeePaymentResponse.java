package rdguez.eduardo.upax.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class EmployeePaymentResponse {

  private Double payment;

  @Builder.Default
  private Boolean success = false;

}
