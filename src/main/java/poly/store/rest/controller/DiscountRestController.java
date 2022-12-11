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

import poly.store.entity.Discount;
import poly.store.model.DiscountModel;
import poly.store.service.DiscountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/discount")
public class DiscountRestController {
	@Autowired
	DiscountService discountService;
	
	@PostMapping("/form")
	public DiscountModel create(@RequestBody DiscountModel discountModel) {
		return discountService.createDiscount(discountModel);
	}
	
	@GetMapping()
	public List<Discount> getAll(){
		return discountService.findAll();
	}
	
	@GetMapping("/form/{id}")
	public DiscountModel getOneUserById(@PathVariable("id") Integer id) {
		return discountService.getOneDiscountById(id);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		discountService.delete(id);
	}
	
	@PutMapping("/form/{id}")
	public DiscountModel update(@PathVariable("id") Integer id, @RequestBody DiscountModel discountModel) {
		return discountService.updateDiscount(discountModel);
	}
	
	@GetMapping("/apply/{code}")
	public Discount getDiscountByCode(@PathVariable("code") String code) {
		return discountService.getDiscountByCode(code);
	}
}
