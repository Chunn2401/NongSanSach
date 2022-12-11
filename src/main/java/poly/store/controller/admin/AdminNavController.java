package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;

@Controller
public class AdminNavController {
	@GetMapping("/admin/nav1/form")
	public String form(Model model) {
		model.addAttribute("enableBtnUpdate", false);
		return Constants.USER_DISPLAY_ADMIN_NAV_FORM;
	}

	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang quan ly nhan vien
	 */
	@GetMapping("/admin/nav1/list")
	public String list(Model model) {
		return Constants.USER_DISPLAY_ADMIN_NAV_LIST;
	}

	@GetMapping("/admin/nav1/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("nav1Id", id);
		model.addAttribute("enableBtnUpdate", true);
		return Constants.USER_DISPLAY_ADMIN_NAV_FORM;
	}
}
