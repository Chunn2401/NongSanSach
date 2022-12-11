package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.model.StatisticalProductDay;
import poly.store.model.StatisticalRevenue;
import poly.store.model.StatisticalTotalOrder;
import poly.store.service.OrderService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class StatisticalRestController {
	@Autowired
	OrderService orderService;

	@GetMapping("/statistical/product/day")
	public List<StatisticalProductDay> productDay() {
		return orderService.listStatisticalProductDay();
	}

	@GetMapping("/statistical/revenue/day/{month}/{year}")
	public List<StatisticalRevenue> listRevenueByDay(@PathVariable("month") int month, @PathVariable("year") int year) {
		return orderService.listStatisticalRevenue(month, year);
	}

	@GetMapping("/statistical/revenue/month/{year}")
	public List<StatisticalRevenue> listRevenueByMonth(@PathVariable("year") int year) {
		return orderService.listStatisticalRevenueByMonth(year);
	}

	@GetMapping("/statistical/revenue/year/{year}")
	public List<StatisticalRevenue> listRevenueByYear(@PathVariable("year") int year) {
		return orderService.listStatisticalRevenueByYear(year);
	}

	@GetMapping("/statistical/order/day/{day}/{month}/{year}")
	public StatisticalTotalOrder listOrderByDay(@PathVariable("day") int day, @PathVariable("month") int month,
			@PathVariable("year") int year) {
		return orderService.getStatisticalTotalOrderOnDay(day, month, year);
	}

	@GetMapping("/statistical/order/month/{month}/{year}")
	public StatisticalTotalOrder listOrderByMonth(@PathVariable("month") int month, @PathVariable("year") int year) {
		return orderService.getStatisticalTotalOrderOnMonth(month, year);
	}

	@GetMapping("/statistical/order/year/{year}")
	public StatisticalTotalOrder listOrderByYear(@PathVariable("year") int year) {
		return orderService.getStatisticalTotalOrderOnYear(year);
	}

	@GetMapping("/statisticalOrder/year")
	public List<Integer> listYear() {
		return orderService.getListYearOrder();
	}

	@GetMapping("/statistical/order/option/{day}/{month}/{year}")
	public StatisticalTotalOrder listOrderByOption(@PathVariable("day") int day, @PathVariable("month") int month,
			@PathVariable("year") int year) {
		return orderService.getStatisticalTotalOrderOnOption(day, month, year);
	}
}
