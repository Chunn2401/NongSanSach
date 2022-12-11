package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.MenuOne;
import poly.store.model.Nav1Model;
import poly.store.service.MenuOneService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nav1")
public class Nav1RestController {
	@Autowired
	MenuOneService menuOneService;
	
	@PostMapping("/form")
	public Nav1Model create(@RequestBody Nav1Model nav1Model) {
		return menuOneService.createNav1(nav1Model);
	}
	
	@GetMapping()
	public List<MenuOne> getAll(){
		return menuOneService.findAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		menuOneService.delete(id);
	}
	
	@GetMapping("/form/{id}")
	public Nav1Model getOneNav1ById(@PathVariable("id") Integer id) {
		return menuOneService.getOneNav1ById(id);
	}
	
	@PutMapping("/form/{id}")
	public Nav1Model update(@PathVariable("id") Integer id, @RequestBody Nav1Model nav1Model) {
		return menuOneService.updateNav1(nav1Model);
	}
}
