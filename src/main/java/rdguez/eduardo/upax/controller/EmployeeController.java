package rdguez.eduardo.upax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rdguez.eduardo.upax.domain.Employee;
import rdguez.eduardo.upax.model.EmployeeRequest;
import rdguez.eduardo.upax.model.EmployeeResponse;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.EmployeeWorkedHoursResponse;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.service.EmployeeWorkedHoursService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @Autowired
  EmployeeWorkedHoursService employeeWorkedHoursService;

  @ResponseBody
  @PostMapping("/add")
  public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
    return employeeService.addEmployee(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/worked_hours")
  public EmployeeResponse addEmployeeWorkedHours(@RequestBody EmployeeWorkedHoursRequest employeeRequest) {
    return employeeWorkedHoursService.addEmployeeWorkedHours(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/job")
  public List<Employee> findEmployeesByJob(@RequestBody EmployeeRequest employeeRequest) {
    return employeeService.findEmployeesByJob(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/total_worked_hours")
  public EmployeeWorkedHoursResponse findTotalWorkedHoursByDates(
    @RequestBody EmployeeWorkedHoursRequest employeeRequest
  ) {
    return employeeWorkedHoursService.findTotalWorkedHoursByDates(employeeRequest);
  }

}
