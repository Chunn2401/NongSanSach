package poly.store.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import poly.store.common.Constants;
import poly.store.entity.Discount;
import poly.store.service.impl.DiscountServiceImpl;

@Controller
public class DiscountController {

    @Autowired
    DiscountServiceImpl discount;

    @GetMapping("/discount")
    public String list(Model model) {
        List<Discount> list = discount.findAll();
        model.addAttribute("items", list);

        return Constants.USER_DISPLAY_DISCOUNT;
    }

}
