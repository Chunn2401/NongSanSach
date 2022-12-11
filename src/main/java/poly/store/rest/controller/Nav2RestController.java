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

import poly.store.entity.MenuTwo;
import poly.store.model.Nav2Model;
import poly.store.service.MenuTwoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/nav2")
public class Nav2RestController {
	@Autowired
	MenuTwoService menuTwoService;
	
	@PostMapping("/form")
	public Nav2Model create(@RequestBody Nav2Model nav2Model) {
		return menuTwoService.createNav2(nav2Model);
	}
	
	@GetMapping()
	public List<MenuTwo> getAll(){
		return menuTwoService.findAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		menuTwoService.delete(id);
	}
	
	@GetMapping("/form/{id}")
	public Nav2Model getOneNav2ById(@PathVariable("id") Integer id) {
		return menuTwoService.getOneNav2ById(id);
	}
	
	@PutMapping("/form/{id}")
	public Nav2Model update(@PathVariable("id") Integer id, @RequestBody Nav2Model nav2Model) {
		return menuTwoService.updateNav2(nav2Model);
	}
}
