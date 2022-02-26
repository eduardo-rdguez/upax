package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import rdguez.eduardo.upax.util.DateUtil;

import java.time.LocalDate;
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
    EmployeeResponse employeeResponse = EmployeeResponse.builder().build();
    Long employeeId = employeeWorkedHoursRequest.getEmployeeId();
    Date workedDate = employeeWorkedHoursRequest.getWorkedDate();
    Integer workedHours = employeeWorkedHoursRequest.getWorkedHours();

    if (validateWorkedDate(workedDate) && validateWorkedHours(workedHours)) {
      Optional<EmployeeWorkedHours> employeeWorkedHours = findWorkedHoursByEmployeeIdAndWorkedDate(
        employeeId, workedDate
      );

      if (employeeWorkedHours.isPresent()) {
        return employeeResponse;
      } else {
        Optional<Employee> employee = employeeService.findEmployeeById(employeeId);

        if (employee.isPresent()) {
          return saveEmployeeWorkedHoursBy(employeeWorkedHoursRequest, employee.get());
        }
      }
    }
    return employeeResponse;
  }

  private boolean validateWorkedDate(Date workedDate) {
    LocalDate workedLocalDate = DateUtil.toLocalDate(workedDate);
    LocalDate currentLocalDate = DateUtil.currentLocalDate();

    return currentLocalDate.isAfter(workedLocalDate);
  }

  private boolean validateWorkedHours(Integer workedHours) {
    return workedHours <= Constants.HOURS_ALLOWED_WORKED;
  }

  @Transactional(readOnly = true)
  private Optional<EmployeeWorkedHours> findWorkedHoursByEmployeeIdAndWorkedDate(Long id, Date workedDate) {
    return employeeWorkedHoursRepository.findOneByEmployee_IdAndWorkedDate(id, workedDate);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
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
  @Transactional(readOnly = true)
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
    EmployeeWorkedHoursResponse workedHoursResponse = EmployeeWorkedHoursResponse.builder().build();

    if (!employeeWorkedHoursList.isEmpty()) {
      return EmployeeWorkedHoursDto.toResponse(totalWorkedHours);
    }
    return workedHoursResponse;
  }

  private Integer sumEmployeeWorkedHoursBy(List<EmployeeWorkedHours> employeeWorkedHoursList) {
    return employeeWorkedHoursList
      .stream()
      .map(EmployeeWorkedHours::getWorkedHours)
      .reduce(0, Integer::sum);
  }
}
