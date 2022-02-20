package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rdguez.eduardo.upax.constant.Constants;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.domain.EmployeeWorkedHours;
import rdguez.eduardo.upax.dto.EmployeeWorkedHoursDto;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursResponse;
import rdguez.eduardo.upax.repository.EmployeeWorkedHoursRepository;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.service.EmployeeWorkedHoursService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeWorkedHoursServiceImpl implements EmployeeWorkedHoursService {

  @Autowired
  EmployeeWorkedHoursRepository employeeWorkedHoursRepository;

  @Autowired
  EmployeeService employeeService;

  @Override
  public EmployeeResponse addEmployeeWorkedHours(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  ) {
    Long employeeId = employeeWorkedHoursRequest.getEmployeeId();
    Integer workedHours = employeeWorkedHoursRequest.getWorkedHours();
    Date workedDate = employeeWorkedHoursRequest.getWorkedDate();

    Optional<EmployeeWorkedHours> employeeWorkedHours = findWorkedHoursEmployeeIdAndWorkedDate(
      employeeId, workedDate
    );
    EmployeeResponse employeeResponse = EmployeeResponse.builder().build();

    if (employeeWorkedHours.isPresent()) {
      return employeeResponse;
    } else {
      Optional<Employee> employee = employeeService.findEmployeeById(employeeId);

      if (employee.isPresent() && validateWorkedHours(workedHours)) {
        return saveEmployeeWorkedHoursBy(employeeWorkedHoursRequest, employee.get());
      }
    }
    return employeeResponse;
  }

  private Optional<EmployeeWorkedHours> findWorkedHoursEmployeeIdAndWorkedDate(Long id, Date workedDate) {
    return employeeWorkedHoursRepository.findOneByEmployee_IdAndWorkedDate(id, workedDate);
  }

  private boolean validateWorkedHours(Integer workedHours) {
    return workedHours <= Constants.ALLOWED_HOURS_WORKED;
  }

  private EmployeeResponse saveEmployeeWorkedHoursBy(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest,
    Employee employee
  ) {
    EmployeeWorkedHours newEmployeeWorkedHours = EmployeeWorkedHoursDto.toEntity(
      employeeWorkedHoursRequest, employee
    );
    EmployeeWorkedHours employeeWorkedHours = employeeWorkedHoursRepository.save(newEmployeeWorkedHours);

    return EmployeeWorkedHoursDto.toResponse(employeeWorkedHours);
  }

  @Override
  public List<EmployeeWorkedHours> findWorkedHoursByDates(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  ) {
    Long employeeId = employeeWorkedHoursRequest.getEmployeeId();
    Date startDate = employeeWorkedHoursRequest.getStartDate();
    Date endDate = employeeWorkedHoursRequest.getEndDate();

    return employeeWorkedHoursRepository.findAllByEmployee_IdAndWorkedDateBetween(
      employeeId,
      startDate,
      endDate
    );
  }

  @Override
  public EmployeeWorkedHoursResponse findTotalWorkedHoursByDates(
    EmployeeWorkedHoursRequest employeeWorkedHoursRequest
  ) {
    List<EmployeeWorkedHours> employeeWorkedHoursList = findWorkedHoursByDates(
      employeeWorkedHoursRequest
    );
    Integer totalWorkedHours = sumEmployeeWorkedHoursBy(employeeWorkedHoursList);

    return EmployeeWorkedHoursResponse.builder()
      .total_worked_hours(totalWorkedHours)
      .success(!employeeWorkedHoursList.isEmpty())
      .build();
  }

  private Integer sumEmployeeWorkedHoursBy(List<EmployeeWorkedHours> employeeWorkedHoursList) {
    return employeeWorkedHoursList
      .stream()
      .map(EmployeeWorkedHours::getWorkedHours)
      .reduce(0, Integer::sum);
  }
}
