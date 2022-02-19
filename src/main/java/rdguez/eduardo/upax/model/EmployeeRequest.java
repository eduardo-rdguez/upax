package rdguez.eduardo.upax.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {

  @JsonProperty("name")
  private String name;

  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("birthdate")
  private Date birthdate;

  @JsonProperty("gender_id")
  private Long genderId;

  @JsonProperty("job_id")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Long jobId;

}
