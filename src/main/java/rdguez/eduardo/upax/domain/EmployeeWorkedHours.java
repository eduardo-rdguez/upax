package rdguez.eduardo.upax.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE_WORKED_HOURS")
public class EmployeeWorkedHours {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "WORKED_HOURS")
  private Integer workedHours;

  @Column(name = "WORKED_DATE")
  private Date workedDate;

  @ManyToOne
  @JoinColumn(name = "EMPLOYEE_ID")
  private Employee employee;

}
