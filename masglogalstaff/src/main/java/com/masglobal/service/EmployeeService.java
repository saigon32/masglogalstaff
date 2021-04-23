package com.masglobal.service;

import java.util.List;

import com.masglobal.dto.Employee;

public interface EmployeeService {

	public double calculateSalary(String contractTypeName, double salary);

	public double calculateHourlySalary(double salary);

	public double calculateMonthlySalary(double salary);

	public List<Employee> calculateAllSalary(List<Employee> employees);
}
