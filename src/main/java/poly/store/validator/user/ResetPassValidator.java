
package poly.store.validator.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import poly.store.model.ResetPassword;

/**
 * Class bat loi form reset-password.html
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Component
public class ResetPassValidator implements Validator {

	/**
	 * Lien ket class UserRegister voi class bat loi
	 * 
	 * @param clazz
	 * @return Ket qua co dung hay khong
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ResetPassword.class;
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
		ResetPassword userRegister = (ResetPassword) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userRegister.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank.userRegister.confirmPassword");
		
		if(!errors.hasFieldErrors()) {
			if(userRegister.getConfirmPassword().equals(userRegister.getPassword()) == false) {
				errors.rejectValue("confirmPassword", "NotDuplicate.userRegister.confirmPassword");
			}
		}
		
		if(!errors.hasFieldErrors("password")) {
			if(userRegister.getPassword().length() < 7) {
				errors.rejectValue("password", "Min.userRegister.password");
			}
			if(userRegister.getPassword().length() > 15) {
				errors.rejectValue("password", "Max.userRegister.password");
			}
		}
	}

}
