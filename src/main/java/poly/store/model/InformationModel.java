package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationModel {
	private String fullName;
	private String email;
	private String password;
	private String birthday;
	private int gender;
	private int news;
}
