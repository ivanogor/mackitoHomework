package pro.sky.mackitohomework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.mackitohomework.exception.EmployeeAlreadyAddedException;
import pro.sky.mackitohomework.exception.EmployeeNotFoundException;
import pro.sky.mackitohomework.exception.EmployeeStorageIsFullException;
import pro.sky.mackitohomework.model.Employee;
import pro.sky.mackitohomework.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("department") int department,
                                @RequestParam("salary") double salary) {
        try {
            Employee employee = new Employee(firstName, lastName, department, salary);
            return employeeService.addEmployee(employee);
        }
        catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            throw e;
        }
    }

    @RequestMapping("/remove")
    public Employee removeEmployee(@RequestParam("fullName") String fullName) {
        try {
            return employeeService.removeEmployee(fullName);
        }
        catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @RequestMapping("/find")
    public Employee findEmployee(@RequestParam("fullName") String fullName) {
        try {
            return employeeService.findEmployee(fullName);
        }
        catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @RequestMapping("/display")
    public List<Employee> displayEmployees() {
        return employeeService.displayEmployees();
    }
}
