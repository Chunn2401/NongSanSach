package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountModel {
	private int id;
	private String name;
	private String code;
	private int price;
	private int quality;
	private String applyDay;
	private String expiration;
	private int moneyLimit;
}
