package pro.sky.mackitohomework.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.mackitohomework.exception.EmployeeAlreadyAddedException;
import pro.sky.mackitohomework.exception.EmployeeNotFoundException;
import pro.sky.mackitohomework.exception.EmployeeStorageIsFullException;
import pro.sky.mackitohomework.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final Map<String, Employee> employeeBook = new HashMap<>();
    @Override
    public Employee addEmployee(Employee employee) {
        int MAX_NUMBER_OF_EMPLOYEE = 10;
        if (employeeBook.size() < MAX_NUMBER_OF_EMPLOYEE) {
            String name = employee.getFirstName() + employee.getLastName();
            checkArguments(name);
            if (!employeeBook.containsKey(name)) {
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
    public Employee findEmployee(String fullName) {
        checkArguments(fullName);
        if (employeeBook.containsKey(fullName)) {
            return employeeBook.get(fullName);
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public Employee removeEmployee(String fullName) {
        checkArguments(fullName);
        Employee employee = employeeBook.get(fullName);
        if (employeeBook.remove(fullName, employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public List<Employee> displayEmployees() {
        return new ArrayList<>(employeeBook.values());
    }

    private void checkArguments(String name) {
        boolean badOutcome = !isAlpha(name);
        if (badOutcome) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
