package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.model.DetailOrder;
import poly.store.model.OrderModel;
import poly.store.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/order")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	@GetMapping("/pending")
	public List<OrderModel> getListOrder() {
		return orderService.listOrderGroupByCode();
	}
	
	@GetMapping("/shipping")
	public List<OrderModel> getListOrderShipping() {
		return orderService.listOrderGroupByCodeShipping();
	}
	
	@GetMapping("/success")
	public List<OrderModel> getListOrderSuccess() {
		return orderService.listOrderGroupByCodeSuccess();
	}
	
	@GetMapping("/cancel")
	public List<OrderModel> getListOrderCancel() {
		return orderService.listOrderGroupByCodeCancel();
	}
	
	@PutMapping("/shipped/{id}")
	public void shipped(@PathVariable("id") String id) {
		orderService.shippedOrder(id);
	}
	
	@GetMapping("/pending/{id}")
	public DetailOrder listOrderByCodeAndUsername(@PathVariable("id") String id) {
		return orderService.getDetailOrderByCode(id);
	}
	
	@PutMapping("/approve/{id}")
	public void approve(@PathVariable("id") String id) {
		orderService.approveOrder(id);
	}
	
	@PutMapping("/cancel/{id}")
	public void cancel(@PathVariable("id") String id) {
		orderService.cancelOrder(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id) {
		orderService.deleteOrder(id);
	}
}
