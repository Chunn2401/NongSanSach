
package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

/**
 * Class de hien thi trang chu quan tri
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Controller
public class AdminIndexController {
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang admin index.html
	 */
	@GetMapping("/admin/index")
	public String index(Model model) {
		return Constants.USER_DISPLAY_ADMIN_INDEX;
	}
}
