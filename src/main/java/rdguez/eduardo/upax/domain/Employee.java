package rdguez.eduardo.upax.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEES")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "BIRTHDATE")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date birthdate;

  @ManyToOne
  @JoinColumn(name = "GENDER_ID")
  private Gender gender;

  @ManyToOne
  @JoinColumn(name = "JOB_ID")
  private Job job;

}
