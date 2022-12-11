package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
	private String fullName;
	private String detail;
	private String phone;
	private String province;
	private String district;
	private String ward;
}
