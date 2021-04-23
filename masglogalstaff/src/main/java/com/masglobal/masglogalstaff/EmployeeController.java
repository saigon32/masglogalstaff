package com.masglobal.masglogalstaff;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masglobal.dto.Employee;
import com.masglobal.service.EmployeeServiceImpl;

@Controller
@ComponentScan("com")
@RequestMapping("/mgstaff")
public class EmployeeController {

	private String url = "http://masglobaltestapi.azurewebsites.net/api/employees";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	private static final String PATH = "/error";

	/**
	 * external api: http://localhost:8084/mgstaff/employees
	 * 
	 * @return
	 */
	@GetMapping("/employees")
	public @ResponseBody List<Object> employees() {
		Object[] objects = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(objects);
	}

	/**
	 * owner api: http://localhost:8084/mgstaff/calculateSalary
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@GetMapping("/calculateSalary")
	public @ResponseBody List<ArrayList<Employee>> calculateSalary() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ArrayList<Employee> employees = JSON_MAPPER.readValue(new URL(url), JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Employee.class));
		for (Employee e : employees) {
			e.setSalary(employeeServiceImpl.calculateSalary(e.contractTypeName, e.getHourlySalary()));
		}
		return Arrays.asList(employees);
	}

	/**
	 * complete list of employees for jsp, with filter by id
	 * 
	 * @param model
	 * @param id
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@RequestMapping("/employeesList")
	public String employeesList(Model model, @RequestParam(value = "search_employee", required = false) String id) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ArrayList<Employee> employees = JSON_MAPPER.readValue(new URL(url), JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Employee.class));
		if (id != null && !id.equals("")) {
			List<Employee> employeesFilter = new ArrayList<Employee>();
			try {
				Employee filterEmployee = employeeServiceImpl.findById(employees, Integer.valueOf(id));
				filterEmployee.setSalary(employeeServiceImpl.calculateSalary(filterEmployee.getContractTypeName(), filterEmployee.getHourlySalary()));
				employeesFilter.add(filterEmployee);
			} catch (NumberFormatException e) {
				System.err.println("Debes ingresar id numerico");
			}
			model.addAttribute("employeesList", employeesFilter);
		} else {
			employees = (ArrayList<Employee>) employeeServiceImpl.calculateAllSalary(employees);
			model.addAttribute("employeesList", employees);
		}
		return "employeesList";
	}

	/**
	 * management for error in app
	 * 
	 * @return
	 */
	@RequestMapping(value = "/error")
	public String myerror() {
		return "error";
	}

	public String getErrorPath() {
		return PATH;
	}
}
