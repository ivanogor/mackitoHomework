package pro.sky.mackitohomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.mackitohomework.model.Employee;
import pro.sky.mackitohomework.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> displayEmployees(@PathVariable int id) {
        return departmentService.displayEmployeesByDepartment(id);
    }

    @GetMapping("/{id}/salary/sum")
    public double sumSalary(@PathVariable int id){
        return departmentService.sumSalary(id);
    }

    @GetMapping("/{id}/salary/max")
    public double findEmployeeWithBiggestSalary(@PathVariable int id){
        return departmentService.findEmployeeWithBiggestSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public double findEmployeeWithLowestSalary(@PathVariable int id){
        return departmentService.findEmployeeWithLowestSalary(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> displayEmployees(){
        return departmentService.displayEmployees();
    }
}
