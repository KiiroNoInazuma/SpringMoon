package com.app.springmoon.services;

import com.app.springmoon.models.Employee;

public interface EmployeeDataInformedService {
    void addEmployee(Employee employee);

    String printAllEmp();

    String getEmpMinSalary();

    String getEmpMaxSalary();
}
