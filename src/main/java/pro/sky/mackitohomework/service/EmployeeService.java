package pro.sky.mackitohomework.service;

import pro.sky.mackitohomework.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    Employee findEmployee(String fullName);
    Employee removeEmployee(String fullName);
    List<Employee> displayEmployees();
}
