package poly.store.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;
import poly.store.dao.OrderDao;
import poly.store.model.StatisticalTotalOrder;
import poly.store.service.OrderService;

@Controller
public class AdminStatisticalController {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderService orderService;
	
	
	@GetMapping("/admin/statistical/product/day")
	public String product(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_PRODUCT_DAY;
	}
	
	@GetMapping("/admin/statistical/revenue")
	public String revenue(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_REVENUE;
	}
	
	@GetMapping("/admin/statistical/order")
	public String order(Model model) {
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_ORDER;
	}
	
	@GetMapping("/demo")
	public String demo(Model model) {
		StatisticalTotalOrder totalOrder = orderService.getStatisticalTotalOrderOnOption(0, 11, 2021);
		//List<Integer> listYear = orderService.getListYearOrder();
		
		//int max = orderDao.getMaxYearOrder();
		//int min = orderDao.getMinYearOrder();
		
		model.addAttribute("success", totalOrder.getOrderSuccess());
		model.addAttribute("wait", totalOrder.getOrderWait());
		model.addAttribute("cancel", totalOrder.getOrderCancel());
		model.addAttribute("transport", totalOrder.getOrderTransport());
		
		return "admin/demo";
	}
}
