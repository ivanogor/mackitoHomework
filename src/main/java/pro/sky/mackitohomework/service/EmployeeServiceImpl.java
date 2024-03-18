package pro.sky.mackitohomework.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.mackitohomework.exception.*;
import pro.sky.mackitohomework.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final int MAX_NUMBER_OF_EMPLOYEE = 5;
    private Map<String, Employee> employeeBook = new HashMap<>();
    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        if (employeeBook.size() < MAX_NUMBER_OF_EMPLOYEE) {
            String name = firstName + lastName;
            checkArguments(name);
            if (!employeeBook.containsKey(name)) {
                Employee employee = new Employee(firstName, lastName, department, salary);
                employeeBook.put(name, employee);
                return employee;
            } else {
                throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
            }
        } else {
            throw new EmployeeStorageIsFullException("ArrayIsFull");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String name = firstName + lastName;
        checkArguments(name);
        if (employeeBook.containsKey(name)) {
            return employeeBook.get(name);
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String name = firstName + lastName;
        checkArguments(name);
        Employee employee = employeeBook.get(name);
        if (employeeBook.remove(name, employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public Collection<Employee> displayEmployees() {
        return Collections.unmodifiableCollection(employeeBook.values());
    }

    private void checkArguments(String name) {
        boolean badOutcome = !isAlpha(name);
        if (badOutcome) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
