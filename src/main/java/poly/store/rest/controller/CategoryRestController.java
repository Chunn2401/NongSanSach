
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

import poly.store.entity.Category;
import poly.store.model.CategoryModel;
import poly.store.service.CategoryService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 * 
 * @author khoa-ph
 * @version 1.00
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/form")
	public CategoryModel create(@RequestBody CategoryModel categoryModel) {
		return categoryService.createCategory(categoryModel);
	}
	
	@GetMapping()
	public List<Category> getAll(){
		return categoryService.findAll();
	}
	
	@GetMapping("/form/{id}")
	public CategoryModel getOneUserById(@PathVariable("id") Integer id) {
		return categoryService.getOneCategoryById(id);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		categoryService.delete(id);
	}
	
	@PutMapping("/form/{id}")
	public CategoryModel update(@PathVariable("id") Integer id, @RequestBody CategoryModel categoryModel) {
		return categoryService.updateCategory(categoryModel);
	}
}
