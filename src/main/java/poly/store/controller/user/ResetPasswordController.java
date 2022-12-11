
package poly.store.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.common.Constants;
import poly.store.entity.User;
import poly.store.model.ResetPassword;
import poly.store.service.UserService;
import poly.store.validator.user.ResetPassValidator;

/**
 * Class lam moi lai mat khau
 * 
 * @author khoa-ph
 * @version 1.00
 *
 */
@Controller
public class ResetPasswordController {

	// Thong tin bat loi form
	@Autowired
	ResetPassValidator resetPassValidator;

	// Thong tin user service
	@Autowired
	UserService userService;

	// Thong tin ma hoa password
	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	 * Rang buoc form voi trinh bat loi
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == ResetPassword.class) {
			binder.setValidator(resetPassValidator);
		}
	}

	@GetMapping("/reset-password")
	public String displayFormResetPassword(Model model, @RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "email", required = true) String email) {
		ResetPassword userForm = new ResetPassword();
		model.addAttribute("userForm", userForm);	
		User user = userService.findUserByEmail(email);
		if (user == null) {		
			return "redirect:/404page";
		} else {
			if (passwordEncoder.matches(user.getPassword(), code) == false) {
				return "redirect:/404page";
			}
		}
		return Constants.USER_DISPLAY_RESET_PASSWORD;
	}

	@PostMapping("/reset-password")
	public String handlerFormResetPassword(Model model, @RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "email", required = true) String email,
			@ModelAttribute("userForm") @Validated ResetPassword userForm, BindingResult result) {
		if (result.hasErrors()) {
			return Constants.USER_DISPLAY_RESET_PASSWORD;
		} else {
			System.out.println("đúng 1");
			User user = userService.findUserByEmail(email);
			if (user == null) {
				return "redirect:/404page";
			} else {
				if (passwordEncoder.matches(user.getPassword(), code)) {
					user.setPassword(userForm.getPassword());
					userService.save(user);
					model.addAttribute("alert", "Chúc mừng!");
					model.addAttribute("message", "Cập nhật tài khoản thành công!");
					return Constants.USER_DISPLAY_ALERT_STATUS;
				} else {
					return "redirect:/404page";
				}
			}
		}
	}
}
