package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.store.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowProduct {
	private Product product;
	private int totalStar;
}
