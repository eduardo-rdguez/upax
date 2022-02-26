package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.dto.EmployeeDto;
import rdguez.eduardo.upax.model.request.EmployeeRequest;
import rdguez.eduardo.upax.model.response.EmployeeResponse;
import rdguez.eduardo.upax.repository.EmployeeRepository;
import rdguez.eduardo.upax.service.EmployeeJobService;

import java.util.List;

@Service
@Slf4j
public class EmployeeJobServiceImpl implements EmployeeJobService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  @Transactional(readOnly = true)
  public EmployeeResponse findEmployeesByJob(EmployeeRequest employeeRequest) {
    Long id = employeeRequest.getJobId();
    List<Employee> employees = employeeRepository.findAllByJob_Id(id);
    return EmployeeDto.toResponse(employees);
  }

}
