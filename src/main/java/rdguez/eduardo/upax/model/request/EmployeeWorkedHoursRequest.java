package rdguez.eduardo.upax.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import rdguez.eduardo.upax.model.request.groups.AddWorkedHours;
import rdguez.eduardo.upax.model.request.groups.FindWorkedHours;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@Setter
public class EmployeeWorkedHoursRequest {

  @JsonProperty("employee_id")
  @NotNull(groups = {AddWorkedHours.class, FindWorkedHours.class})
  private Long employeeId;

  @JsonProperty("worked_hours")
  @NotNull(groups = AddWorkedHours.class)
  @Max(value = 20, groups = AddWorkedHours.class)
  private Integer workedHours;

  @JsonProperty("worked_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull(groups = AddWorkedHours.class)
  private Date workedDate;

  @JsonProperty("start_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull(groups = FindWorkedHours.class)
  private Date startDate;

  @JsonProperty("end_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  @NotNull(groups = FindWorkedHours.class)
  private Date endDate;

}
