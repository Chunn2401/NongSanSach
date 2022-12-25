
package poly.store.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.common.Constants;
import poly.store.entity.Manufacturer;
import poly.store.entity.Product;
import poly.store.model.ShowProduct;
import poly.store.service.CommentService;
import poly.store.service.ManufacturerService;
import poly.store.service.ProductService;
import poly.store.service.UserRoleService;

/**
 * Class de hien thi trang chu nguoi dung
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Controller
public class IndexController {
	@Autowired
	UserRoleService userRoleService;

	@Autowired
	ProductService productService;

	@Autowired
	ManufacturerService manufacturerService;

	@Autowired
	CommentService commentService;

	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang index.html
	 */
	@GetMapping("/home")
	public String index(Model model) {
		return Constants.USER_DISPLAY_INDEX;
	}

	@GetMapping("/")
		public String load(Model model) {
			return Constants.USER_DISPLAY_LOAD;
		}

	@ModelAttribute("manufacturer")
	public List<Manufacturer> manufacture(Model model) {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	@ModelAttribute("latestProduct")
	public List<List<ShowProduct>> getLatestProduct(Model model) {
		List<Product> list = productService.getListLatestProduct();

		List<ShowProduct> temp = new ArrayList<>();

		List<List<ShowProduct>> result = new ArrayList<List<ShowProduct>>();

		for (int i = 0; i < list.size(); i++) {
			int totalStar = commentService.getAllStarCommentByProductNameSearch(list.get(i).getNamesearch());

			ShowProduct showProduct = new ShowProduct();
			showProduct.setProduct(list.get(i));
			showProduct.setTotalStar(totalStar);
			temp.add(showProduct);
			if (i % 2 != 0) {
				result.add(temp);
				temp = new ArrayList<>();
			}
			if (i == (list.size() - 1)) {
				if (i % 2 == 0) {
					result.add(temp);
					temp = new ArrayList<>();
				}
			}
		}

		return result;
	}

	// @ModelAttribute("searchProduct")
	// public List<List<ShowProduct>> searchProducts(@RequestParam("search") String search, Model model){

	// 	List<Product> list = productService.searchProducts(search);

	// 	List<ShowProduct> temp = new ArrayList<>();

	// 	List<List<ShowProduct>> result = new ArrayList<List<ShowProduct>>();
	// 	// hiển thị comment và đánh giá từ người dùng
	// 	for (int i = 0; i < list.size(); i++) {
	// 		int totalStar = commentService.getAllStarCommentByProductNameSearch(list.get(i).getNamesearch());

	// 		ShowProduct showProduct = new ShowProduct();
	// 		showProduct.setProduct(list.get(i));
	// 		showProduct.setTotalStar(totalStar);
	// 		temp.add(showProduct);
	// 		if (i % 2 != 0) {
	// 			result.add(temp);
	// 			temp = new ArrayList<>();
	// 		}
	// 		if (i == (list.size() - 1)) {
	// 			if (i % 2 == 0) {
	// 				result.add(temp);
	// 				temp = new ArrayList<>();
	// 			}
	// 		}
	// 	}
	// 	return result;
	// }

	@ModelAttribute("viewsProduct")
	public List<ShowProduct> getViewsProduct(Model model) {
		List<Product> list = productService.getListViewsProduct();
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}
}
