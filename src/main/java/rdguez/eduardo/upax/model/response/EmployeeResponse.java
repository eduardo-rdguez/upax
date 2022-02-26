package rdguez.eduardo.upax.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rdguez.eduardo.upax.domain.Employee;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class EmployeeResponse {

  private List<Employee> employees;

  @Builder.Default
  private Boolean success = false;

}
