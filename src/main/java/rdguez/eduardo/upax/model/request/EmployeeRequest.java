package rdguez.eduardo.upax.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import rdguez.eduardo.upax.model.request.groups.AddEmployee;
import rdguez.eduardo.upax.model.request.groups.FindByJob;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EmployeeRequest {

  @JsonProperty("name")
  @NotBlank(groups = AddEmployee.class)
  private String name;

  @JsonProperty("last_name")
  @NotBlank(groups = AddEmployee.class)
  private String lastName;

  @JsonProperty("birthdate")
  @NotNull(groups = AddEmployee.class)
  private Date birthdate;

  @JsonProperty("gender_id")
  @NotNull(groups = AddEmployee.class)
  private Long genderId;

  @JsonProperty("job_id")
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull(groups = {AddEmployee.class, FindByJob.class})
  private Long jobId;

}
