package poly.store.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.entity.Product;
import poly.store.model.CartModel;
import poly.store.model.ShowProduct;
import poly.store.service.CategoryService;
import poly.store.service.CommentService;
import poly.store.service.ProductService;
import poly.store.service.SessionService;
import poly.store.service.impl.ShoppingCartServiceImpl;

@Controller
public class DetailProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SessionService sessionService;

	@Autowired
	CommentService commentService;

	@Autowired
	ShoppingCartServiceImpl cartService;

	@GetMapping("/san-pham/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model) {
		productService.updateView(nameSearch);
		model.addAttribute("infor", false);
		sessionService.set("sessionProduct", cartService);
		return Constants.USER_DISPLAY_DETAIL_PRODUCT;
	}

	@SuppressWarnings("static-access")
	@PostMapping("/san-pham/{nameSearch}")
	public String orderProduct(@PathVariable("nameSearch") String nameSearch, Model model, HttpServletRequest req) {

		Product product = productService.getProductByNameSearch(nameSearch);

		Map<Integer, CartModel> map = cartService.map;
		CartModel cartModel = map.get(product.getId());

		if (cartModel == null) {
			cartModel = new CartModel();
			cartModel.setId(product.getId());
			cartModel.setProduct(product);
			cartModel.setQuality(1);
			cartService.add(product.getId(), cartModel);
		}

		else {
			cartService.update(cartModel.getId(), cartModel.getQuality() + 1);
		}

		model.addAttribute("infor", true);

		sessionService.set("sessionProduct", cartService);

		return Constants.USER_DISPLAY_DETAIL_PRODUCT;
	}

	@ModelAttribute("inforProduct")
	public Product inforCategory(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		return product;
	}

	@ModelAttribute("listProductRelated")
	public List<ShowProduct> listProductRelated(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		List<Product> list = productService.getListProductRelated(product.getManufacturer().getId());

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product item : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(item.getNamesearch());
			showProduct.setProduct(item);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}

	@ModelAttribute("countComment")
	public int countComment(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getCountCommentByProductNameSearch(nameSearch);
		return result;
	}

	@ModelAttribute("totalStar")
	public int totalStar(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getAllStarCommentByProductNameSearch(nameSearch);
		System.out.println(result);
		return result;
	}
}
