
package poly.store.controller.user;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.entity.Role;
import poly.store.entity.User;
import poly.store.entity.UserRole;
import poly.store.model.UserRegister;
import poly.store.service.RoleService;
import poly.store.service.SessionService;
import poly.store.service.UserRoleService;
import poly.store.service.UserService;
import poly.store.service.impl.MailerServiceImpl;
import poly.store.validator.user.RegisterFormValidator;

/**
 * Class tao moi mot tai khoan
 * 
 * @author khoa-ph
 * @version 1.00
 *
 */
@Controller
public class RegisterController {
	// Thong tin bat loi form
	@Autowired
	RegisterFormValidator registerFormValidator;

	// Thong tin user service
	@Autowired
	UserService userService;

	// Class cung cap cac service lam viec voi session
	@Autowired
	SessionService sessionService;

	// Class cung cap service gui mail
	@Autowired
	MailerServiceImpl mailerService;

	// Thong tin role service
	@Autowired
	RoleService roleService;

	// Thong tin user role service
	@Autowired
	UserRoleService userRoleService;

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
		if (target.getClass() == UserRegister.class) {
			binder.setValidator(registerFormValidator);
		}
	}

	/**
	 * Hien thi trang register
	 * 
	 * @param model
	 * @return man hinh register
	 */
	@GetMapping("/register")
	public String displayFormRegister(Model model) {
		// Rang buoc form ten userRegister voi model UserRegister.class
		UserRegister userRegister = new UserRegister();
		model.addAttribute("userRegister", userRegister);

		// Hien thi trang register
		return Constants.USER_DISPLAY_REGISTER;
	}

	/**
	 * Xu ly form register
	 * 
	 * @param model
	 * @param userRegister
	 * @param result
	 * @return Trang register neu co loi. Nguoc lai qua trang xac thuc email.
	 */
	@PostMapping("/register")
	public String handlerRegisterForm(Model model, @ModelAttribute("userRegister") @Validated UserRegister userRegister,
			BindingResult result) {
		if (result.hasErrors()) {
			return Constants.USER_DISPLAY_REGISTER;
		} else {
			if (userRegister.isConfirmTerm() == false) {
				model.addAttribute("checkTerm", true);
				return Constants.USER_DISPLAY_REGISTER;
			} else {
				// Random ma xac nhan co 6 chu so
				int code = (int) Math.floor(((Math.random() * 899999) + 100000));
				userRegister.setCode(code);
				// Gui ma xac nhan qua mail
				mailerService.queue(userRegister.getEmail(), "Xác nhận email!", "Code xác nhận của bạn là: " + code);

				// Luu thong tin vao session user de tien hanh xac nhan ma
				sessionService.set("user", userRegister);
			}
		}
		return Constants.USER_DISPLAY_CONFIRM_CODE;
	}

	/**
	 * Hien thi man hinh dang ky neu nguoi dung refresh page Muc dich la han che
	 * nguoi dung truy cap trai phep
	 * 
	 * @param model
	 * @return trang register
	 */
	@GetMapping("/register/confirm-code")
	public String displayFormConfirmMail(Model model) {
		// Hien thi trang register
		return "redirect:/register";
	}

	/**
	 * Xu ly trang confirm-code
	 * 
	 * @param model
	 * @param userRegisterForm
	 * @param result
	 * @return Trang dang ky thanh cong.
	 */
	@PostMapping("/register/confirm-code")
	public String handlerFormConfirmMail(Model model,
			@ModelAttribute("userRegister") @Validated UserRegister userRegisterForm, BindingResult result) {
		// Lay thong tin session user
		UserRegister userRegister = sessionService.get("user");
		// Bat loi bo trong field
		if (userRegisterForm.getConfirmCode().isEmpty()) {
			result.rejectValue("confirmCode", "NotBlank.userRegister.confirmCode");
		} else {
			// Bat loi ma xac nhan nguoi dung nhap khong trung voi code
			if (!userRegisterForm.getConfirmCode().equals(String.valueOf(userRegister.getCode()))) {
				result.rejectValue("confirmCode", "NotDuplicate.userRegister.confirmCode");
			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				// Them moi mot user
				User user = new User();
				user.setEmail(userRegister.getEmail());
				user.setFullname(userRegister.getFullName());
				user.setPassword(userRegister.getPassword());
				user.setCreateday(timestamp.toString());
				user.setSubscribe(userRegister.getSubscribe());
				userService.save(user);
				// Tim thong tin role theo roleId
				Role role = roleService.findRoleById(1);
				// Them moi mot user co vai tro la ROLE_USER
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRoleService.save(userRole);
				// Xoa thong tin session user cu
				sessionService.remove("user");

				// Hien thi man hinh dang ky thanh cong
				model.addAttribute("alert", "Đăng ký thành công!");
				model.addAttribute("message", "Chúc mừng bạn đã tạo mới một tài khoản thành công!");
				return Constants.USER_DISPLAY_ALERT_STATUS;
			}
		}

		// Hien thi lai man hinh xac nhan neu co loi
		return Constants.USER_DISPLAY_CONFIRM_CODE;
	}
}
