package pro.sky.mackitohomework.service;

import org.springframework.stereotype.Service;
import pro.sky.mackitohomework.exception.EmptyDepartmentException;
import pro.sky.mackitohomework.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public double findEmployeeWithBiggestSalary(int id) {
        return employeeService.displayEmployees().stream()
                .filter(employee -> employee.getDepartment() == id)
                .mapToDouble(Employee::getSalary)
                .max()
                .orElseThrow(EmptyDepartmentException::new);
    }

    @Override
    public double findEmployeeWithLowestSalary(int id) {

        return employeeService.displayEmployees().stream()
                .filter(employee -> employee.getDepartment() == id)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(EmptyDepartmentException::new);
    }

    @Override
    public double sumSalary(int id) {
        return employeeService.displayEmployees().stream()
                .filter(employee -> employee.getDepartment() == id)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public List<Employee> displayEmployeesByDepartment(int id) {
        long countOfEmployeesInDepartment = employeeService.displayEmployees().stream()
                .filter(employee -> employee.getDepartment() == id)
                .count();

        if (countOfEmployeesInDepartment == 0) {
            throw new EmptyDepartmentException();
        }

            return employeeService.displayEmployees().stream()
                    .filter(employee -> employee.getDepartment() == id)
                    .collect(Collectors.toList());
    }

        @Override
        public Map<Integer, List<Employee>> displayEmployees () {
            return employeeService.displayEmployees().stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment));
        }
    }
