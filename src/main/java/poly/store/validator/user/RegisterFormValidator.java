
package poly.store.validator.user;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import poly.store.entity.User;
import poly.store.model.UserRegister;
import poly.store.service.UserService;

/**
 * Class bat loi form register.html
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Component
public class RegisterFormValidator implements Validator {

	// Thong tin user service
	@Autowired
	UserService userService;

	// Kiem tra dinh dang email
	private EmailValidator emailValidator = EmailValidator.getInstance();

	/**
	 * Lien ket class UserLogin voi class bat loi
	 * 
	 * @param clazz
	 * @return Ket qua co dung hay khong
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserRegister.class;
	}

	/**
	 * Kiem tra form co thoa dieu kien
	 * 
	 * @param target
	 * @param errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		// Lien ket Object voi UserRegister class
		UserRegister userRegister = (UserRegister) target;
		// Kiem tra co bo trong field ho va ten
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotBlank.userRegister.fullname");
		// Kiem tra co bo trong field email
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank.userRegister.email");
		// Kiem tra co bo trong field mat khau
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userRegister.password");
		// Kiem tra co bo trong field xac nhan mat khau
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank.userRegister.confirmPassword");

		// Kiem tra tai khoan da ton tai hay chua
		if (!errors.hasFieldErrors("email")) {
			// Kiem tra email co dung dinh dang
			if (!this.emailValidator.isValid(userRegister.getEmail())) {
				errors.rejectValue("email", "NotPattern.userRegister.email");
			} else {
				User user = userService.findUserByEmail(userRegister.getEmail());
				if (user != null) {
					errors.rejectValue("email", "Duplicate.userRegister.email");
				}
			}
		}

		// Kiem tra mat khau co dung dinh dang
		if (!errors.hasFieldErrors("password")) {
			if (userRegister.getPassword().length() < 7) {
				errors.rejectValue("password", "Min.userRegister.password");
			}
			if (userRegister.getPassword().length() > 30) {
				errors.rejectValue("password", "Max.userRegister.password");
			}
		}

		// Kiem tra xac nhan mat khau co trung khop
		if (!errors.hasFieldErrors("confirmPassword")) {
			if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "NotDuplicate.userRegister.confirmPassword");
			}
		}
		
	}

}
