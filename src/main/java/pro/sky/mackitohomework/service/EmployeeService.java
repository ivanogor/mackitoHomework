package pro.sky.mackitohomework.service;

import pro.sky.mackitohomework.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, double salary);
    Employee findEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Collection<Employee> displayEmployees();
}
