package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rdguez.eduardo.upax.constant.Constants;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.Gender;
import rdguez.eduardo.upax.domain.Job;
import rdguez.eduardo.upax.dto.EmployeeDto;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.repository.EmployeeRepository;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.service.GenderService;
import rdguez.eduardo.upax.service.JobService;
import rdguez.eduardo.upax.util.DateUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  GenderService genderService;

  @Autowired
  JobService jobService;

  @Override
  @Transactional(readOnly = true)
  public Optional<Employee> findEmployeeById(Long id) {
    return employeeRepository.findById(id);
  }

  @Override
  public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
    Optional<Employee> employee = findEmployeeByNameAndLastName(employeeRequest);
    EmployeeResponse employeeResponse = EmployeeResponse.builder().build();

    if (employee.isEmpty()) {
      Optional<Gender> gender = genderService.findGenderById(employeeRequest.getGenderId());
      Optional<Job> job = jobService.findJobById(employeeRequest.getJobId());
      boolean legalAgeToWork = validateEmployeeAge(employeeRequest);

      if (legalAgeToWork && gender.isPresent() && job.isPresent()) {
        return saveEmployeeBy(employeeRequest, gender.get(), job.get());
      }
    }

    return employeeResponse;
  }

  @Transactional(readOnly = true)
  private Optional<Employee> findEmployeeByNameAndLastName(EmployeeRequest employeeRequest) {
    String name = employeeRequest.getName();
    String lastName = employeeRequest.getLastName();

    return employeeRepository.findOneByNameAndLastName(name, lastName);
  }

  private boolean validateEmployeeAge(EmployeeRequest employeeRequest) {
    LocalDate birthdate = DateUtil.toLocalDate(employeeRequest.getBirthdate());
    LocalDate currentDate = DateUtil.currentLocalDate();
    int age = Period.between(birthdate, currentDate).getYears();

    return age >= Constants.LEGAL_AGE;
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  private EmployeeResponse saveEmployeeBy(EmployeeRequest employeeRequest, Gender gender, Job job) {
    Employee newEmployee = EmployeeDto.toEntity(employeeRequest, gender, job);
    Employee employee = employeeRepository.save(newEmployee);

    return EmployeeDto.toResponse(employee);
  }

}
