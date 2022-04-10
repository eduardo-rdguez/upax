package rdguez.eduardo.upax.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@ToString
public class EmployeePaymentResponse {

  private BigDecimal payment;

  @Builder.Default
  private Boolean success = false;

}
