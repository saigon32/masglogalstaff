package com.masglobal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masglobal.dto.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	/**
	 * calculate salary by contract type
	 */
	public double calculateSalary(String contractTypeName, double salary) {
		double salaryEmployee = 0;
		if ("HourlySalaryEmployee".equalsIgnoreCase(contractTypeName)) {
			salaryEmployee = calculateHourlySalary(salary);
		} else if ("MonthlySalaryEmployee".equalsIgnoreCase(contractTypeName)) {
			salaryEmployee = calculateMonthlySalary(salary);
		}
		return salaryEmployee;
	}

	/**
	 * calculate all salary for all employees
	 */
	public List<Employee> calculateAllSalary(List<Employee> employees) {
		for (Employee e : employees) {
			e.setSalary(calculateSalary(e.getContractTypeName(), e.getHourlySalary()));
		}
		return employees;
	}

	@Override

	/**
	 * when calculate salary by HourlySalary: 120 * HourlySalary * 12
	 * 
	 * @param salary
	 * @return
	 */
	public double calculateHourlySalary(double salary) {
		double hourlySalary = 120 * salary * 12;
		return hourlySalary;
	}

	/**
	 * when calculate salary by MonthtlySalary: MonthtlySalary * 12
	 * 
	 * @param salary
	 * @return
	 */
	public double calculateMonthlySalary(double salary) {
		double monthlySalary = salary * 12;
		return monthlySalary;
	}

	/**
	 * retrieve a employee filtering by id
	 * 
	 * @param employees
	 * @param id
	 * @return
	 */
	public Employee findById(List<Employee> employees, int id) {
		Employee employee = null;
		for (Employee e : employees) {
			if (e.getId() == id)
				employee = e;
		}
		return employee;
	}

}
