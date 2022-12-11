package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.District;
import poly.store.entity.Province;
import poly.store.entity.Ward;
import poly.store.model.AddressModel;
import poly.store.service.AddressService;

/**
 * Class cung cap cac dich vu rest api cho bang employee
 * 
 * @author khoa-ph
 * @version 1.00
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AddressRestController {
	@Autowired
	AddressService addressService;
	
	@GetMapping("/province")
	public List<Province> list(){
		return addressService.findAllProvince();
	}
	
	@GetMapping("/district/{id}")
	public List<District> listDistrict(@PathVariable("id") Integer id){
		return addressService.findDistrictByIdProvince(id);
	}
	
	@GetMapping("/ward/{idProvince}/{idDistrict}")
	public List<Ward> listWard(@PathVariable("idProvince") Integer idProvince, @PathVariable("idDistrict") Integer idDistrict){
		return addressService.findWardByIdProvinceAndIdDistrict(idProvince, idDistrict);
	}
	
	@PostMapping("/address/form")
	public AddressModel create(@RequestBody AddressModel addressModel) {
		return addressService.createAddress(addressModel);
	}
	
	@GetMapping("/address/update/{id}")
	public AddressModel getOneAddressById(@PathVariable("id") int id) {
		return addressService.getOneAddressById(id);
	}
	
	@GetMapping("/update/district/{id}")
	public List<District> getListDistrictById(@PathVariable("id") Integer id){
		return addressService.getListDistrictByAdressId(id);
	}
	
	@GetMapping("/update/ward/{id}")
	public List<Ward> getListWardById(@PathVariable("id") Integer id){
		return addressService.getListWardByAdressId(id);
	}
	
	@PutMapping("/address/form/{id}")
	public AddressModel update(@PathVariable("id") Integer id, @RequestBody AddressModel addressModel) {
		return addressService.updateAddress(addressModel, id);
	}
}
