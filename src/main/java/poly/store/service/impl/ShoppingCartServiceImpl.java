package poly.store.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.store.entity.Discount;
import poly.store.model.CartModel;
import poly.store.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	public static Map<Integer, CartModel> map = new HashMap<>();

	public static Map<Integer, Discount> mapDiscount = new HashMap<>();

	@Override
	public void add(Integer id, CartModel entity) {
		if (map.get(id) != null) {
			this.update(id, entity.getQuality() + 1);
		} else {
			map.put(id, entity);
		}

	}

	@Override
	public void addDiscount(Integer id, Discount entity) {
		mapDiscount.put(id, entity);
	}

	@Override
	public double getAmount() {
		double amount = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			amount += map.get(i).getQuality() * map.get(i).getProduct().getPrice();
		}

		if (this.getDiscount() != null) {
			// System.out.println("true");
			try {
				amount = amount - this.getDiscount().getPrice();
			} catch (Exception e) {

			}
			System.out.println(amount);
		}

		return amount;
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public void update(Integer id, int qty) {
		map.get(id).setQuality(qty);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<CartModel> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count++;
		}
		return count;
	}

	@Override
	public int getCountAllProduct() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count += map.get(i).getQuality();
		}
		return count;
	}

	@Override
	public Discount getDiscount() {
		Discount discount = new Discount();
		Set<Integer> set = mapDiscount.keySet();
		for (Integer i : set) {
			discount = mapDiscount.get(i);
		}
		return discount;
	}

	@Override
	public void clearDiscount() {
		mapDiscount.clear();
	}

}
