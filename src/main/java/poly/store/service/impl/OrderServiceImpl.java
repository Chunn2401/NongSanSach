package poly.store.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.OrderDao;
import poly.store.dao.ProductDao;
import poly.store.entity.Discount;
import poly.store.entity.Order;
import poly.store.entity.Product;
import poly.store.model.CartModel;
import poly.store.model.DetailOrder;
import poly.store.model.OrderModel;
import poly.store.model.StatisticalOrder;
import poly.store.model.StatisticalProductDay;
import poly.store.model.StatisticalRevenue;
import poly.store.model.StatisticalTotalOrder;
import poly.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDao orderDao;

	@Autowired
	ProductDao productDao;

	@Override
	public List<Order> getOrderByName(String code) {
		return orderDao.getOrderByName(code);
	}

	@Override
	public void save(Order order) {
		orderDao.save(order);
	}

	@Override
	public List<OrderModel> listOrderHistory() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		List<OrderModel> list = orderDao.listOrderHistory(username);

		for (OrderModel order : list) {
			String[] date = order.getDate().split("-");
			String result = date[2] + "/" + date[1] + "/" + date[0];
			order.setDate(result);
		}

		return list;
	}

	@Override
	public List<Order> listOrderByCodeAndUsername(String id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		List<Order> list = orderDao.listOrderByCodeAndUsername(id, username);

		for (Order order : list) {
			String[] date = order.getDate().split("-");
			String result = date[2] + "/" + date[1] + "/" + date[0];
			order.setDate(result);
		}

		return list;
	}

	@Override
	public List<OrderModel> listOrderGroupByCode() {
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodePending();

		for (OrderModel list : listOrder) {
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	@Override
	public DetailOrder getDetailOrderByCode(String id) {
		DetailOrder detailOrder = new DetailOrder();
		List<Order> listOrder = orderDao.getOrderByName(id);

		detailOrder.setId(listOrder.get(0).getCode());
		detailOrder.setAddress(listOrder.get(0).getAddress().getDetail());
		detailOrder.setComment(listOrder.get(0).getComment());
		detailOrder.setDate(listOrder.get(0).getDate());

		Discount discount = listOrder.get(0).getDiscount();
		if (discount != null) {
			detailOrder.setDiscount(discount.getPrice());
		} else {
			detailOrder.setDiscount(0);
		}

		detailOrder.setDistrict(listOrder.get(0).getAddress().getDistrict());
		detailOrder.setFullName(listOrder.get(0).getAddress().getUser().getFullname());
		String method = "";
		if (listOrder.get(0).getMethod().equals("1")) {
			method = "Thanh toán online khi nhận hàng";
		}
		if (listOrder.get(0).getMethod().equals("0")) {
			method = "Thanh toán trực tiếp khi nhận hàng";
		}
		detailOrder.setMethod(method);
		detailOrder.setPhone(listOrder.get(0).getAddress().getPhone());
		detailOrder.setProvince(listOrder.get(0).getAddress().getProvince());
		detailOrder.setWard(listOrder.get(0).getAddress().getWard());
		int subTotal = 0;
		int total = 0;

		List<CartModel> listCartModel = new ArrayList<CartModel>();
		for (Order list : listOrder) {
			CartModel cartModel = new CartModel();
			Product product = new Product();
			product = list.getProduct();
			cartModel.setProduct(product);
			cartModel.setQuality(list.getQuality());
			listCartModel.add(cartModel);

			subTotal = subTotal + list.getProduct().getPrice() * list.getQuality();
		}
		total = subTotal + 50000 - detailOrder.getDiscount();

		detailOrder.setSubTotal(subTotal);
		detailOrder.setTotal(total);
		detailOrder.setListOrder(listCartModel);

		return detailOrder;
	}

	@Override
	public void approveOrder(String id) {
		List<Order> listOrder = orderDao.getOrderByName(id);
		for (Order list : listOrder) {
			list.setStatus("1");
			orderDao.save(list);
		}
	}

	@Override
	public void cancelOrder(String id) {
		List<Order> listOrder = orderDao.getOrderByName(id);
		for (Order list : listOrder) {
			Product product = list.getProduct();
			product.setQuality(product.getQuality() + list.getQuality());
			list.setStatus("3");
			orderDao.save(list);
			productDao.save(product);
		}
	}

	@Override
	public List<OrderModel> listOrderGroupByCodeShipping() {
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodeShipping();

		for (OrderModel list : listOrder) {
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	@Override
	public void shippedOrder(String id) {
		List<Order> listOrder = orderDao.getOrderByName(id);
		for (Order list : listOrder) {
			list.setStatus("2");
			orderDao.save(list);
		}
	}

	@Override
	public List<OrderModel> listOrderGroupByCodeSuccess() {
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodeSuccess();

		for (OrderModel list : listOrder) {
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	@Override
	public List<OrderModel> listOrderGroupByCodeCancel() {
		List<OrderModel> listOrder = orderDao.listOrderGroupByCodeCancel();

		for (OrderModel list : listOrder) {
			Order order = orderDao.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		return listOrder;
	}

	@Override
	public void deleteOrder(String id) {
		List<Order> listOrder = orderDao.getOrderByName(id);
		for (Order list : listOrder) {
			orderDao.delete(list);
		}
	}

	@Override
	public List<StatisticalProductDay> listStatisticalProductDay() {
		return orderDao.listStatisticalProductDay();
	}

	@Override
	public List<StatisticalRevenue> listStatisticalRevenue(int month, int year) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.MONTH, month - 1);

		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();

		for (int i = 1; i <= maxDay; i++) {
			long sum = 0;

			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderDao.listStatisticalRevenueDay(i, month, year);

			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					Discount discount = order.getDiscount();
					sum = sum + order.getTotal();
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					sum = sum + 50000;
				}

			}

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(i);
			listRevenue.add(statistical);
		}

		return listRevenue;
	}

	@Override
	public List<StatisticalRevenue> listStatisticalRevenueByMonth(int year) {
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		for (int i = 1; i <= 12; i++) {
			long sum = 0;
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderDao.listStatisticalRevenueMonth(i, year);

			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					Discount discount = order.getDiscount();
					sum = sum + order.getTotal();
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					sum = sum + 50000;
				}

			}

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(i);
			listRevenue.add(statistical);

		}

		return listRevenue;
	}

	@Override
	public List<StatisticalRevenue> listStatisticalRevenueByYear(int year) {
		int minYear = year - 10;
		List<StatisticalRevenue> listRevenue = new ArrayList<StatisticalRevenue>();
		for (int i = 1; i <= 10; i++) {
			long sum = 0;
			List<OrderModel> listOrder = new ArrayList<OrderModel>();
			listOrder = orderDao.listStatisticalRevenueYear(minYear + i);

			if (!listOrder.isEmpty()) {
				for (OrderModel order : listOrder) {
					Discount discount = order.getDiscount();
					sum = sum + order.getTotal();
					if (discount != null) {
						sum = sum - discount.getPrice();
					}
					sum = sum + 50000;
				}

			}

			double total = (double) sum / 1000000;

			StatisticalRevenue statistical = new StatisticalRevenue();
			statistical.setPrice(total);
			statistical.setDate(minYear + i);
			listRevenue.add(statistical);

		}

		return listRevenue;

	}

	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year) {
		List<StatisticalOrder> orderSuccess = orderDao.getMaxOrderSuccessOnDay(day, month, year);
		List<StatisticalOrder> orderWait = orderDao.getMaxOrderWaitOnDay(day, month, year);
		List<StatisticalOrder> orderTransport = orderDao.getMaxOrderTransportOnDay(day, month, year);
		List<StatisticalOrder> orderCancel = orderDao.getMaxOrderCancelOnDay(day, month, year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year) {
		List<StatisticalOrder> orderSuccess = orderDao.getMaxOrderSuccessOnMonth(month, year);
		List<StatisticalOrder> orderWait = orderDao.getMaxOrderWaitOnMonth(month, year);
		List<StatisticalOrder> orderTransport = orderDao.getMaxOrderTransportOnMonth(month, year);
		List<StatisticalOrder> orderCancel = orderDao.getMaxOrderCancelOnMonth(month, year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year) {
		List<StatisticalOrder> orderSuccess = orderDao.getMaxOrderSuccessOnYear(year);
		List<StatisticalOrder> orderWait = orderDao.getMaxOrderWaitOnYear(year);
		List<StatisticalOrder> orderTransport = orderDao.getMaxOrderTransportOnYear(year);
		List<StatisticalOrder> orderCancel = orderDao.getMaxOrderCancelOnYear(year);

		int success = orderSuccess.size();
		int wait = orderWait.size();
		int transport = orderTransport.size();
		int cancel = orderCancel.size();

		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder(success, cancel, wait, transport);

		return totalOrder;
	}

	@Override
	public List<Integer> getListYearOrder() {
		int maxYear = orderDao.getMaxYearOrder();
		int minYear = orderDao.getMinYearOrder();

		List<Integer> listYear = new ArrayList<Integer>();

		for (int i = minYear; i <= maxYear; i++) {
			listYear.add(i);
		}

		return listYear;
	}

	@Override
	public StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year) {
		StatisticalTotalOrder totalOrder = new StatisticalTotalOrder();

		if ((day == 0) && (month == 0)) {
			totalOrder = this.getStatisticalTotalOrderOnYear(year);
		} else if (day == 0) {
			totalOrder = this.getStatisticalTotalOrderOnMonth(month, year);
		} else {
			totalOrder = this.getStatisticalTotalOrderOnDay(day, month, year);
		}

		return totalOrder;
	}

}
