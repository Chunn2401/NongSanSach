package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {
	// Thong tin mat khau
	private String password;
	// Thong tin xac nhan mat khau
	private String confirmPassword;
}
