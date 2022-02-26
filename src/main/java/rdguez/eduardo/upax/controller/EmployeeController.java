package rdguez.eduardo.upax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rdguez.eduardo.upax.model.request.EmployeeRequest;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursRequest;
import rdguez.eduardo.upax.model.request.EmployeeWorkedHoursResponse;
import rdguez.eduardo.upax.model.response.EmployeePaymentResponse;
import rdguez.eduardo.upax.model.response.EmployeeResponse;
import rdguez.eduardo.upax.model.response.EmployeeStatusResponse;
import rdguez.eduardo.upax.service.EmployeeJobService;
import rdguez.eduardo.upax.service.EmployeePaymentService;
import rdguez.eduardo.upax.service.EmployeeService;
import rdguez.eduardo.upax.service.EmployeeWorkedHoursService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @Autowired
  EmployeeWorkedHoursService employeeWorkedHoursService;

  @Autowired
  EmployeePaymentService employeePaymentService;

  @Autowired
  EmployeeJobService employeeJobService;

  @ResponseBody
  @PostMapping("/add")
  public EmployeeStatusResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
    return employeeService.addEmployee(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/worked_hours")
  public EmployeeStatusResponse addEmployeeWorkedHours(@RequestBody EmployeeWorkedHoursRequest employeeRequest) {
    return employeeWorkedHoursService.addEmployeeWorkedHours(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/job")
  public EmployeeResponse findEmployeesByJob(@RequestBody EmployeeRequest employeeRequest) {
    return employeeJobService.findEmployeesByJob(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/total_worked_hours")
  public EmployeeWorkedHoursResponse findTotalWorkedHoursByDates(
    @RequestBody EmployeeWorkedHoursRequest employeeRequest
  ) {
    return employeeWorkedHoursService.findTotalWorkedHoursByDates(employeeRequest);
  }

  @ResponseBody
  @PostMapping("/payment")
  public EmployeePaymentResponse findEmployeePaymentByDates(
    @RequestBody EmployeeWorkedHoursRequest employeeRequest
  ) {
    return employeePaymentService.findEmployeePaymentByDates(employeeRequest);
  }

}
