package com.lti;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lti.model.Employee;
import com.lti.service.EmployeeServiceImpl;

// http://localhost:8081/RestEmployee/rest/employees
@Path("employees")
public class EmployeeResource {
	EmployeeServiceImpl service = new EmployeeServiceImpl();

	// http://localhost:9090/RestEmployee/rest/employees
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee employee) {
		System.out.println(employee);
		service.addEmployee(employee);
		return Response.status(201, "Employee "+employee.getEmployeeId()+
				" is created sucessfully").build();
	}
	
	// http://localhost:9090/RestEmployee/rest/employees
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getAllEmployees(){
		return service.findAllEmployees();
	}
	
	// http://localhost:9090/RestEmployee/rest/employees/100
	@GET
	@Path("{emp_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("emp_id") int employeeId) {
		return service.findEmployee(employeeId);
	}
	
	// http://localhost:9090/RestEmployee/rest/employees/100
	@DELETE
	@Path("{emp_id}")
	public Response deleteEmployee(@PathParam("emp_id") int employeeId) {
		int result = service.removeEmployee(employeeId);
		if(result == 1) {
			return Response.status(200).build();
		}else {
			return Response.status(400).build();
		}
	}
	
	// http://localhost:9090/RestEmployee/rest/employees
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee employee) {
		int reuslt = service.modifyEmployee(employee);
		if(reuslt == 1) {
			return Response.status(200).build();
		}else {
			return Response.status(400).build();
		}
	}
}



