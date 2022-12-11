package poly.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.model.EmployeeForm;
import poly.store.service.GeneralService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/form/employee")
public class GeneralRestController {
	@Autowired
	GeneralService generalService;
	
	@PostMapping
	public EmployeeForm create(@RequestBody EmployeeForm employee) {
		return generalService.createEmployee(employee);
	}
	
	@GetMapping("{id}")
	public EmployeeForm getOneUserById(@PathVariable("id") Integer id) {
		return generalService.getOneUserById(id);
	}
	
	@PutMapping("{id}")
	public EmployeeForm update(@PathVariable("id") Integer id, @RequestBody EmployeeForm employeeForm) {
		return generalService.updateEmployee(employeeForm);
	}
}
