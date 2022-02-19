package rdguez.eduardo.upax.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class EmployeeWorkedHoursRequest {

  @JsonProperty("employee_id")
  private Long employeeId;

  @JsonProperty("worked_hours")
  private Integer workedHours;

  @JsonProperty("worked_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date workedDate;

}
