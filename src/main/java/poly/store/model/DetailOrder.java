package poly.store.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailOrder {
	private String id;
	private String date;
	private String method;
	private String fullName;
	private String address;
	private String phone;
	private String province;
	private String district;
	private String ward;
	private int subTotal;
	private int discount;
	private int total;
	private String comment;
	private List<CartModel> listOrder;
}
