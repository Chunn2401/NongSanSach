
package poly.store.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

/**
 * Class hien thi man hinh loi
 * khi nguoi dung truy cap khong dung vai tro
 * 
 * @author khoa-ph
 * @version 1.00
 *
 */
@Controller
public class Page404Controller {

	/**
	 * Hien thi page khi nguoi dung truy cap khong dung vai tro
	 * 
	 * @param model
	 * @return page 404
	 */
	@GetMapping("/404page")
	public String display404Page(Model model) {
		return Constants.USER_DISPLAY_404_PAGE;
	}
}
