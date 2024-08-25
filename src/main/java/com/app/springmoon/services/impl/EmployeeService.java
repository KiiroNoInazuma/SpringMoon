package com.app.springmoon.services.impl;

import com.app.springmoon.models.Employee;
import com.app.springmoon.services.EmployeeDataInformedService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class EmployeeService implements EmployeeDataInformedService {
    private Employee[] arrEmployees = new Employee[10];

    @Override
    public void addEmployee(Employee employee) {
        boolean check = false;
        for (int i = 0; i < arrEmployees.length; i++) {
            if (arrEmployees[i] == null) {
                arrEmployees[i] = employee;
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("Невозможно добавить нового сотрудника. Освободите место");
        }
    }

    public void distinct() {
        arrEmployees = Arrays.stream(arrEmployees).distinct().toArray(Employee[]::new);
    }

    public void deleteEmployee(int id) {
        IntStream.range(0, arrEmployees.length)
                .filter(i -> arrEmployees[i] != null && arrEmployees[i].getId() == id)
                .forEach(i -> arrEmployees[i] = null);
    }

    private Stream<Employee> getEmpStream() {
        return Arrays.stream(arrEmployees).filter(Objects::nonNull);
    }

    public void printSalaryMonthSum() {
        double sum = getEmpStream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Сумма затрат на зарплаты в месяц: " + sum);
    }

    public void doIndexSalary(int per) {
        double getPer = 1 + (double) per / 100;
        getEmpStream().forEach(s -> s.setSalary((int) (s.getSalary() * getPer)));
    }

    @Override
    public String printAllEmp() {
        return Arrays.stream(arrEmployees)
                .map(element -> Objects.isNull(element) ? "" : element.toString()).collect(Collectors.joining("<br>"));
    }

    @Override
    public String getEmpMinSalary() {
        String result = getEmpStream()
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getFullName)
                .orElse("Список пуст");

        return "Сотрудник с минимальной зарплатой: " + result;
    }

    @Override
    public String getEmpMaxSalary() {
        String result = getEmpStream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getFullName)
                .orElse("Список пуст");

        return "Сотрудник с максимальной зарплатой: " + result;
    }

    public void printAvgSalaryMonth() {
        double average = getEmpStream().mapToDouble(Employee::getSalary).average().orElse(0.0);
        System.out.printf("Среднее значение зарплат: %.2f", average);
    }

    public void printAllEmpFio() {
        getEmpStream()
                .map(Employee::getFullName)
                .forEach(System.out::println);
    }

    public void printDepNumMin(String dep) {
        String result = getEmpStream()
                .filter(s -> s.getDepartment().equals(dep))
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getFullName)
                .orElse("Список пуст");

        System.out.printf("Сотрудник с минимальной зарплатой в департаменте <%s> : %s", dep, result);
    }

    public void printDepNumMax(String dep) {
        String result = getEmpStream()
                .filter(s -> s.getDepartment().equals(dep))
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getFullName)
                .orElse("Список пуст");

        System.out.printf("Сотрудник с максимальной зарплатой в департаменте <%s> : %s", dep, result);
    }

    public void printDepNumSalaryMonthSum(String dep) {
        double sum = getEmpStream()
                .filter(s -> s.getDepartment().equals(dep))
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.println("Сумма затрат на зарплаты в месяц: " + sum);
    }

    public void printDepNumAvgSalaryMonth(String dep) {
        double average = getEmpStream()
                .filter(employee -> employee.getDepartment().equals(dep))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.printf("Среднее значение зарплат: %.2f", average);
    }

    public void doDepNumIndexSalary(int per, String dep) {
        double getPer = 1 + (double) per / 100;
        getEmpStream()
                .filter(employee -> employee.getDepartment().equals(dep))
                .forEach(s -> s.setSalary((int) (s.getSalary() * getPer)));
    }

    public void printDepNumAllEmp(String dep) {
        getEmpStream()
                .filter(s -> s.getDepartment().equals(dep))
                .forEach(System.out::println);
    }

    public void printEmpLessNum(double num) {
        getEmpStream()
                .filter(s -> s.getSalary() < num)
                .forEach(System.out::println);
    }

    public void printEmpMoreNum(double num) {
        getEmpStream()
                .filter(s -> s.getSalary() >= num)
                .forEach(System.out::println);
    }

    public void changeSalaryOrDepartment(String name, double sum) {
        Arrays.stream(arrEmployees)
                .filter(s -> s.getFullName().equals(name))
                .findFirst()
                .ifPresent(s -> s.setSalary(sum));
    }

    public void changeSalaryOrDepartment(String name, String dep) {
        getEmpStream()
                .filter(s -> s.getFullName().equals(name))
                .findFirst()
                .ifPresent(s -> s.setDepartment(dep));
    }
}
