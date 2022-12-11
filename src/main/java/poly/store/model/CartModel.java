package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.store.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartModel {
	private int id;
	private Product product;
//	private String name;
//	private String image;
	private int quality;
//	private int discount = 0;
//	private int price;
}
