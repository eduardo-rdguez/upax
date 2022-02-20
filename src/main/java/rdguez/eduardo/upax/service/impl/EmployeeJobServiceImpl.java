package rdguez.eduardo.upax.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.repository.EmployeeRepository;
import rdguez.eduardo.upax.service.EmployeeJobService;

import java.util.List;

@Service
@Slf4j
public class EmployeeJobServiceImpl implements EmployeeJobService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findEmployeesByJob(EmployeeRequest employeeRequest) {
    Long id = employeeRequest.getJobId();
    return employeeRepository.findAllByJob_Id(id);
  }

}
