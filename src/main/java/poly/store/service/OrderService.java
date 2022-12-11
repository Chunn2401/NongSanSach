package poly.store.service;

import java.util.List;

import poly.store.entity.Order;
import poly.store.model.DetailOrder;
import poly.store.model.OrderModel;
import poly.store.model.StatisticalProductDay;
import poly.store.model.StatisticalRevenue;
import poly.store.model.StatisticalTotalOrder;

public interface OrderService {

	List<Order> getOrderByName(String code);

	void save(Order order);

	List<OrderModel> listOrderHistory();

	List<Order> listOrderByCodeAndUsername(String id);

	List<OrderModel> listOrderGroupByCode();

	DetailOrder getDetailOrderByCode(String id);

	void approveOrder(String id);

	void cancelOrder(String id);

	List<OrderModel> listOrderGroupByCodeShipping();

	void shippedOrder(String id);

	List<OrderModel> listOrderGroupByCodeSuccess();

	List<OrderModel> listOrderGroupByCodeCancel();

	void deleteOrder(String id);

	List<StatisticalProductDay> listStatisticalProductDay();

	List<StatisticalRevenue> listStatisticalRevenue(int month, int year);

	List<StatisticalRevenue> listStatisticalRevenueByMonth(int year);

	List<StatisticalRevenue> listStatisticalRevenueByYear(int year);

	StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year);

	StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year);

	StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year);

	List<Integer> getListYearOrder();

	StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year);
}
