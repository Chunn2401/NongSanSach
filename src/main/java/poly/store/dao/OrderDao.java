package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Order;
import poly.store.model.OrderModel;
import poly.store.model.StatisticalOrder;
import poly.store.model.StatisticalProductDay;

public interface OrderDao extends JpaRepository<Order, Integer> {
	@Query("SELECT o FROM Order o WHERE o.code = ?1")
	List<Order> getOrderByName(String code);

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.address.user.email = ?1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderHistory(String email);

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 0 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodePending();

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeShipping();

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 2 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeSuccess();

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 3 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeCancel();

	@Query("SELECT o FROM Order o WHERE o.code = ?1 and o.address.user.email = ?2")
	List<Order> listOrderByCodeAndUsername(String code, String username);

	@Query("SELECT new StatisticalProductDay(o.product.code, o.product.name, o.product.price, o.product.quality, sum(o.quality)) FROM Order o WHERE CAST(GETDATE() as date) = o.date GROUP BY o.product.code, o.product.name, o.product.price, o.product.quality")
	List<StatisticalProductDay> listStatisticalProductDay();

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listStatisticalRevenueMonth(int month, int year);

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listStatisticalRevenueYear(int year);

	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listStatisticalRevenueDay(int day, int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnDay(int day, int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnDay(int day, int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnDay(int day, int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnDay(int day, int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnMonth(int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnMonth(int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnMonth(int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnMonth(int month, int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnYear(int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnYear(int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnYear(int year);

	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnYear(int year);

	@Query("SELECT MAX(YEAR(o.date)) FROM Order o")
	int getMaxYearOrder();

	@Query("SELECT MIN(YEAR(o.date)) FROM Order o")
	int getMinYearOrder();
}
