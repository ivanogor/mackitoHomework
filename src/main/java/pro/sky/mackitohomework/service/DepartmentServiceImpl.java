package pro.sky.mackitohomework.service;

import org.springframework.stereotype.Service;
import pro.sky.mackitohomework.model.Employee;

import java.util.HashMap;
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
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public double findEmployeeWithLowestSalary(int id) {
        return employeeService.displayEmployees().stream()
                .filter(employee -> employee.getDepartment() == id)
                .mapToDouble(Employee::getSalary)
                .min()
                .orElseThrow(RuntimeException::new);
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
        return employeeService.displayEmployees().stream()
                .filter(employee -> employee.getDepartment() == id)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> displayEmployees() {
        Map<Integer, List<Employee>> result = new HashMap<>();

        for (int i = 0; i < 5; i++) {

            int finalI = i;
            result.put(i, employeeService.displayEmployees().stream()
                    .filter(employee -> employee.getDepartment() == finalI)
                    .collect(Collectors.toList()));
        }

        return result;
    }
}