package pro.sky.mackitohomework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.mackitohomework.controller.DepartmentController;
import pro.sky.mackitohomework.model.Employee;
import pro.sky.mackitohomework.service.DepartmentService;
import pro.sky.mackitohomework.service.EmployeeService;

import javax.lang.model.element.Name;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @Mock
    private DepartmentService departmentServiceMock;

    @InjectMocks
    private DepartmentController out;

    private static Employee EMPLOYEE0 = new Employee("Иван", "Огорь", 2, 56000);
    private static String NAME_FOR_FIND_EMPLOYEE0 = "ИванОгорь";

    private static List<Employee> LIST_OF_EMPLOYEE = new ArrayList<>(List.of(
            new Employee("Иван", "Огорь", 2, 56000),
            new Employee("Валентина", "Огорь", 1, 53000),
            new Employee("Дмитрий", "Великанов", 2, 67000),
            new Employee("Алексей", "Нечаев", 4, 40000),
            new Employee("Владимир", "Козлов", 2, 37000)
    ));

    //не понимаю почему возваращает 0 в findEmployeeWithBiggestSalary
    @Test
    public void shouldReturnMaxSalaryInDepartment(){
        when(employeeServiceMock.displayEmployees())
                .thenReturn(LIST_OF_EMPLOYEE);



        assertEquals(67000, departmentServiceMock.findEmployeeWithBiggestSalary(2));
    }

    @Test
    public void shouldCallServiceMethodWhenAddRemoveAndFindEmployee(){
        when(employeeServiceMock.addEmployee(EMPLOYEE0))
                .thenReturn(EMPLOYEE0);

        when(employeeServiceMock.findEmployee(NAME_FOR_FIND_EMPLOYEE0))
                .thenReturn(EMPLOYEE0);

        when(employeeServiceMock.removeEmployee(NAME_FOR_FIND_EMPLOYEE0))
                .thenReturn(EMPLOYEE0);

        assertEquals(EMPLOYEE0, employeeServiceMock.addEmployee(EMPLOYEE0));
        assertEquals(EMPLOYEE0, employeeServiceMock.findEmployee(NAME_FOR_FIND_EMPLOYEE0));
        assertEquals(EMPLOYEE0, employeeServiceMock.removeEmployee(NAME_FOR_FIND_EMPLOYEE0));

        verify(employeeServiceMock).addEmployee(EMPLOYEE0);
        verify(employeeServiceMock).findEmployee(NAME_FOR_FIND_EMPLOYEE0);
        verify(employeeServiceMock).removeEmployee(NAME_FOR_FIND_EMPLOYEE0);
    }
}
