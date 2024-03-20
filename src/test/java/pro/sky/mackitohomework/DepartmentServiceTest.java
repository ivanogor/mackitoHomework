package pro.sky.mackitohomework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.mackitohomework.controller.DepartmentController;
import pro.sky.mackitohomework.model.Employee;
import pro.sky.mackitohomework.service.DepartmentServiceImpl;
import pro.sky.mackitohomework.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeServiceImpl employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;



    private static final List<Employee> LIST_OF_EMPLOYEES = new ArrayList<>(List.of(
            new Employee("Иван", "Огорь", 1, 67000),
            new Employee("Валентина", "Огорь", 1, 53000),
            new Employee("Дмитрий", "Великанов", 2, 56000),
            new Employee("Алексей", "Нечаев", 4, 43000),
            new Employee("Владимир", "Козлов", 3, 37000),
            new Employee("Семен", "Степанов", 5, 23000),
            new Employee("Петр", "Иванов", 5, 12000),
            new Employee("Юлия", "Михайлова", 2, 42000),
            new Employee("Игорь", "Гриднев", 3, 40000),
            new Employee("Егор", "Одинцов", 4, 35000)
    ));

    public static Stream<Arguments> provideParamsForMaxSalary() {
        return Stream.of(
                Arguments.of(1, 67000),
                Arguments.of(2, 56000),
                Arguments.of(3, 40000),
                Arguments.of(4, 43000),
                Arguments.of(5, 23000)
        );
    }

    public static Stream<Arguments> provideParamsForMinSalary() {
        return Stream.of(
                Arguments.of(1, 53000),
                Arguments.of(2, 42000),
                Arguments.of(3, 37000),
                Arguments.of(4, 35000),
                Arguments.of(5, 12000)
        );
    }

    public static Stream<Arguments> provideParamsForSumSalary() {
        return Stream.of(
                Arguments.of(1, 120000),
                Arguments.of(2, 98000),
                Arguments.of(3, 77000),
                Arguments.of(4, 78000),
                Arguments.of(5, 35000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMaxSalary")
    public void shouldReturnMaxSalaryInDepartment(int department, double expected){
        when(employeeServiceMock.displayEmployees()).thenReturn(LIST_OF_EMPLOYEES);

        assertEquals(expected, out.findEmployeeWithBiggestSalary(department));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMinSalary")
    public void shouldReturnMinSalaryInDepartment(int department, double expected){
        when(employeeServiceMock.displayEmployees()).thenReturn(LIST_OF_EMPLOYEES);

        assertEquals(expected, out.findEmployeeWithLowestSalary(department));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForSumSalary")
    public void shouldReturnSumSalaryInDepartment(int department, double expected){
        when(employeeServiceMock.displayEmployees()).thenReturn(LIST_OF_EMPLOYEES);

        assertEquals(expected, out.sumSalary(department));
    }

    @Test
    public void shouldReturnMapOfEmployeeGroupingBy(){
        when(employeeServiceMock.displayEmployees()).thenReturn(LIST_OF_EMPLOYEES);

        Map<Integer, List<Employee>> expected =
                new HashMap<>(Map.of(
                        1,
                        new ArrayList<>(List.of(
                                new Employee("Иван", "Огорь", 1, 67000),
                                new Employee("Валентина", "Огорь", 1, 53000))),
                        2,
                        new ArrayList<>(List.of(
                                new Employee("Дмитрий", "Великанов", 2, 56000),
                                new Employee("Юлия", "Михайлова", 2, 42000))),
                        3,
                        new ArrayList<>(List.of(
                                new Employee("Владимир", "Козлов", 3, 37000),
                                new Employee("Игорь", "Гриднев", 3, 40000))),
                        4,
                        new ArrayList<>(List.of(
                                new Employee("Алексей", "Нечаев", 4, 43000),
                                new Employee("Егор", "Одинцов", 4, 35000))),
                        5,
                        new ArrayList<>(List.of(new Employee("Семен", "Степанов", 5, 23000),
                                new Employee("Петр", "Иванов", 5, 12000)))
                ));

        Map<Integer, List<Employee>> actual = out.displayEmployees();

        assertEquals(expected, actual);
    }
}
