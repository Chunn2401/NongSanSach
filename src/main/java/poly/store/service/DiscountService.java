package poly.store.service;

import java.util.List;

import poly.store.entity.Discount;
import poly.store.model.DiscountModel;

public interface DiscountService {

	DiscountModel createDiscount(DiscountModel discountModel);

	List<Discount> findAll();

	DiscountModel getOneDiscountById(Integer id);

	void delete(Integer id);

	DiscountModel updateDiscount(DiscountModel discountModel);

	Discount getDiscountByCode(String code);

}
