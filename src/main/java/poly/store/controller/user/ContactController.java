package poly.store.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

@Controller
public class ContactController {
	@GetMapping("/contact")
	public String index() {
		return Constants.USER_DISPLAY_CONTACT;
	}
}
