
package poly.store.validator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import poly.store.entity.User;
import poly.store.model.UserLogin;
import poly.store.service.UserService;

/**
 * Class bat loi form login.html
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Component
public class LoginFormValidator implements Validator {
	
	// Class cung cap cac ham lam viec voi bang User trong database
	@Autowired	
	UserService userService;

	/**
	 * Lien ket class UserLogin voi class bat loi
	 * 
	 * @param clazz
	 * @return Ket qua co dung hay khong
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserLogin.class;
	}

	/**
	 * Kiem tra form co thoa dieu kien
	 * 
	 * @param target
	 * @param errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// Lien ket Object voi UserLogin class
		UserLogin userLogin = (UserLogin) target;
		
		// Bat loi neu nguoi dung khong nhap username
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotBlank.userLogin.username");
		
		// Bat loi neu nguoi dung khong nhap mat khau
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userLogin.password");

		// Xu ly cac truong hop khong nhap dung thong tin nhu trong database
		if (!errors.hasFieldErrors()) {
			// Tim user bang username
			User user = userService.findUserByEmail(userLogin.getUsername());
			
			// Xu ly truong hop khong tim thay user nao
			if (user == null) {
				// Thong bao sai thong tin username
				errors.rejectValue("username", "NotExist.userLogin.username");
				
				// Thong bao sai thong password
				errors.rejectValue("password", "NotExist.userLogin.password");
			} else {
				// Neu username nguoi dung nhap chinh xac
				// Kiem tra password nguoi dung co khop voi password trong database
				if (userLogin.getPassword().equals(user.getPassword()) == false) {
					// Thong bao sai thong tin password
					errors.rejectValue("password", "NotExist.userLogin.password");
				}
			}
		}
	}

}
