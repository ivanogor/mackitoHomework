package pro.sky.mackitohomework.service;

import pro.sky.mackitohomework.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    double findEmployeeWithBiggestSalary(int id);
    double findEmployeeWithLowestSalary(int id);
    double sumSalary(int id);
    List<Employee> displayEmployeesByDepartment(int id);
    Map<Integer, List<Employee>> displayEmployees();
}
