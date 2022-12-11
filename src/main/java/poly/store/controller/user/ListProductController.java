
package poly.store.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.common.Constants;
import poly.store.entity.Category;
import poly.store.entity.Manufacturer;
import poly.store.model.ShowProduct;
import poly.store.service.CategoryService;
import poly.store.service.ManufacturerService;
import poly.store.service.ProductService;
import poly.store.service.SessionService;

/**
 * Class de danh sach san pham
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Controller
public class ListProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SessionService sessionService;

	@Autowired
	ManufacturerService manufacturerService;

	@GetMapping("/danh-sach/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model,
			@RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu, @RequestParam(name = "xep", required = false) String sort) {
		
		Pageable pageable = PageRequest.of(p.orElse(0), 15);

		Page<ShowProduct> listProduct = productService.getListProductByFilter(nameSearch, price, manu, sort, pageable);
		
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("price", price);
		model.addAttribute("manu", manu);
		model.addAttribute("sort", sort);
		model.addAttribute("nameSearch", nameSearch);

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_CATEGORY;
	}

	@ModelAttribute("inforCategory")
	public Category inforCategory(@PathVariable("nameSearch") String nameSearch) {
		Category category = categoryService.getCategoryByNameSearch(nameSearch);
		return category;
	}

	@ModelAttribute("listManu")
	public List<Manufacturer> listManu() {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}
}
