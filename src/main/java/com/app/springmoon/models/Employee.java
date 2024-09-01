package com.app.springmoon.models;

import lombok.Data;

@Data
public class Employee {

    private final String fullName;
    private String department;
    private double salary;
    public static int count;
    private final int id;

    public Employee(String fullName, String department, double salary) {
         count++;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
        id = count;
    }

    @Override
    public String toString() {
        return String.format("%s:  %s, %s, %s", id, fullName, department, salary);
    }
}
