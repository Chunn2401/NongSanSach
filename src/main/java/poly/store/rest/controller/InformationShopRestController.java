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

import poly.store.entity.InformationShop;
import poly.store.model.ShopModel;
import poly.store.service.InformationShopService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class InformationShopRestController {
	@Autowired
	InformationShopService informationShopService;
	
	@PostMapping("/form")
	public ShopModel create(@RequestBody ShopModel shopModel) {
		return informationShopService.createInformationShop(shopModel);
	}
	
	@GetMapping()
	public List<InformationShop> getAll(){
		return informationShopService.findAll();
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		informationShopService.delete(id);
	}
	
	@PutMapping("/form/active/{id}")
	public ShopModel update(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) {
		return informationShopService.updateActive(shopModel);
	}
	
	@GetMapping("/form/{id}")
	public ShopModel getOneShopById(@PathVariable("id") Integer id) {
		return informationShopService.getOneShopById(id);
	}
	
	@PutMapping("/form/{id}")
	public ShopModel updateInformation(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) {
		return informationShopService.updateInformation(shopModel);
	}
}
