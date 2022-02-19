package rdguez.eduardo.upax.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class EmployeeResponse {

  private Long id;

  @Builder.Default
  private Boolean success = false;

}