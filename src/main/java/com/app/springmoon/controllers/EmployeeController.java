package com.app.springmoon.controllers;

import com.app.springmoon.models.Employee;
import com.app.springmoon.services.EmployeeDataInformedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeDataInformedService informedService;


    @PostMapping("/add")
    public void addNewEmployee(@RequestBody Employee employee) {
        informedService.addEmployee(employee);
    }

    @GetMapping("/salary/minmax")
    public String searchEmployeeMinAndMaxSalary() {
        return String.format("%s<br>%s", informedService.getEmpMinSalary(), informedService.getEmpMaxSalary());
    }

    @GetMapping("/all")
    public String getAllEmployee() {
        return informedService.printAllEmp();
    }
}
