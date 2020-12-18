package com.lti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lti.model.Employee;

public class EmployeeDaoImpl {
	private static Connection conn;
	
	private void closeConnection() throws SQLException{
		conn.close();
	}
	
	private void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Rp_03111998");
	}
	
	public int createEmployee(Employee employee) {
	System.out.println(employee.toString());
		int result = 0;
		try {
			openConnection();
			String sql = "insert Into employee(empid, empname, empsal) Values(?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, employee.getEmployeeId());
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setDouble(3, employee.getEmployeeSalary());
			result = pstmt.executeUpdate();
			pstmt.close();
			
			closeConnection();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Employee> readAllEmployee(){
		List<Employee> list = new ArrayList<>();
		try {
			openConnection();
			String sql = "Select * From employee";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int empId = rs.getInt("empid");
				String empName = rs.getString("empname");
				double empSal = rs.getDouble("empsal");
				Employee employee = new Employee(empId, empName, empSal);
				list.add(employee);
			}
			
			closeConnection();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Employee readEmployee(int employeeId) {
		Employee employee = null;
		try {
			openConnection();
			String sql = "Select * From employee Where empid = "+employeeId;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				int empId = rs.getInt("empid");
				String empName = rs.getString("empname");
				double empSal = rs.getDouble("empsal");
				employee = new Employee(empId, empName, empSal);
			}
			
			closeConnection();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	public int deleteEmployee(int employeeId) {
		int result = 0;
		try {
			openConnection();
			
			String sql = "Delete From employee Where empid="+employeeId;
			
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			closeConnection();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateEmployee(Employee employee) {
		int result = 0;
		try {
			openConnection();
			
			String sql = "Update employee Set empname = ?, empsal = ? Where empid = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setDouble(2, employee.getEmployeeSalary());
			pstmt.setInt(3, employee.getEmployeeId());
			result = pstmt.executeUpdate();
			pstmt.close();
			
			closeConnection();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}








