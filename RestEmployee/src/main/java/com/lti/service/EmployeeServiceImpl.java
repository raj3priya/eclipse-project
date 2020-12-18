package com.lti.service;

import java.util.List;

import com.lti.dao.EmployeeDaoImpl;
import com.lti.model.Employee;

public class EmployeeServiceImpl {
	EmployeeDaoImpl dao = new EmployeeDaoImpl();
	
	public boolean addEmployee(Employee employee) {
		int result = dao.createEmployee(employee);
		return (result == 1) ? true : false;
	}
	
	public List<Employee> findAllEmployees() {
		List<Employee> list = dao.readAllEmployee();
		return list;
	}
	
	public Employee findEmployee(int employeeId) {
		Employee employee = dao.readEmployee(employeeId);
		return employee;
	}
	
	public int removeEmployee(int employeeId) {
		int result = dao.deleteEmployee(employeeId);
		return result;
	}
	
	public int modifyEmployee(Employee employee) {
		int result = dao.updateEmployee(employee);
		return result;
	}
}
