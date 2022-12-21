
package poly.store.controller.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import poly.store.common.Constants;

/**
 * Class dung de quan ly nhan vien
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Controller
public class AdminUserController {

    /**
     * Hien thi trang chu cua giao dien nguoi dung
     * 
     * @return trang quan ly nhan vien
     */
    @GetMapping("/admin/admin-user")
    public String list(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        model.addAttribute("username", username);
        return Constants.USER_DISPLAY_ADMIN_USER_LIST;
    }

    /**
     * Hien thi trang chu cua giao dien nguoi dung
     * 
     * @return trang quan ly nhan vien
     */
  
}
