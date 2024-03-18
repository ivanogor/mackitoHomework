package pro.sky.mackitohomework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.mackitohomework.exception.EmployeeAlreadyAddedException;
import pro.sky.mackitohomework.exception.EmployeeNotFoundException;
import pro.sky.mackitohomework.exception.EmployeeStorageIsFullException;
import pro.sky.mackitohomework.model.Employee;
import pro.sky.mackitohomework.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeControllerTest {
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp(){
        employeeService = new EmployeeServiceImpl();

        Employee employee1 = new Employee("Иван", "Огорь", 2, 56000);
        Employee employee2 = new Employee("Валентина", "Огорь", 1, 53000);
        Employee employee3 = new Employee("Дмитрий", "Великанов", 2, 67000);
        Employee employee4 = new Employee("Алексей", "Нечаев", 4, 40000);
        Employee employee5 = new Employee("Владимир", "Козлов", 2, 37000);

        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);
        employeeService.addEmployee(employee4);
        employeeService.addEmployee(employee5);
    }

    @Test
    public void shouldReturnListOfEmployees(){
        List<Employee> actual = employeeService.displayEmployees();

        List<Employee> expected = new ArrayList<>(List.of(
                new Employee("Иван", "Огорь", 2, 56000),
                new Employee("Валентина", "Огорь", 1, 53000),
                new Employee("Дмитрий", "Великанов", 2, 67000),
                new Employee("Алексей", "Нечаев", 4, 40000),
                new Employee("Владимир", "Козлов", 2, 37000)
                ));

        assertTrue(collectionsEqualUnordered(expected, actual));
    }

    @Test
    public void shouldReturnEmployeeStorageIsFullException(){
        Employee employee = new Employee("Егор", "Одинцов", 1, 36000);
        employeeService.addEmployee(employee);

        Employee extraEmployee = new Employee("Владислав", "Тудвасев", 2, 55000);
        assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee(extraEmployee)
        );
    }

    @Test
    public void shouldReturnEmployeeAlreadyAddedException(){
        Employee alreadyExistEmployee = new Employee("Иван", "Огорь", 2, 56000);

        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(alreadyExistEmployee)
        );
    }


    @Test
    public void shouldReturnRemovedEmployee(){
        Employee expected = new Employee("Иван", "Огорь", 2, 56000);
        Employee actual = employeeService.removeEmployee("ИванОгорь");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnFindEmployee(){
        Employee expected = new Employee("Иван", "Огорь", 2, 56000);
        Employee actual = employeeService.findEmployee("ИванОгорь");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmployeeNotFoundException(){

        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.removeEmployee("ВладиславТудвасев")
        );

        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("ВладиславТудвасев")
        );
    }

    private <T> boolean collectionsEqualUnordered(List<T> a, List<T> b){
        if(a == b){
            return true;
        }
        if(a == null || b == null || a.size() != b.size()){
            return false;
        }
        return a.containsAll(b) && b.containsAll(a);
    }
}
