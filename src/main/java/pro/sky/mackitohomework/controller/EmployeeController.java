package pro.sky.mackitohomework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.mackitohomework.model.Employee;
import pro.sky.mackitohomework.service.EmployeeService;
import pro.sky.mackitohomework.exception.*;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("department") int department,
                                @RequestParam("salary") double salary) {
        try {
            return employeeService.addEmployee(firstName, lastName, department, salary);
        }
        catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            throw e;
        }
    }

    @RequestMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {
        try {
            return employeeService.removeEmployee(firstName, lastName);
        }
        catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        }
        catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @RequestMapping("/display")
    public Collection<Employee> displayEmployees() {
        return employeeService.displayEmployees();
    }
}
