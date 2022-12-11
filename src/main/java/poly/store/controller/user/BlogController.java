package poly.store.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.common.Constants;
import poly.store.entity.Blog;
import poly.store.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@GetMapping("/blog")
	public String list(Model model) {
		List<Blog> list = blogService.findAll();
		model.addAttribute("items",list);
		
		return Constants.USER_DISPLAY_BLOGS;
	}
	
	@RequestMapping("/blog/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Blog item = blogService.findById(id);
		/* BlogsModel item = blogsService.getOneBlogsById(id); */
		model.addAttribute("item",item);
		
		return Constants.USER_DISPLAY_BLOGS_DETAILS;
	}
}
