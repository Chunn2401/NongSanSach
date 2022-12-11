
package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Comment;
import poly.store.model.CommentModel;
import poly.store.service.CommentService;

/**
 * Class cung cap cac dich vu rest api cho bang comment
 * 
 * @author khoa-ph
 * @version 1.00
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comment")
public class CommentRestController {
	@Autowired
	CommentService commentService;
	
	@GetMapping("/form/product/{id}")
	public List<Comment> getListCommentByProductId(@PathVariable("id") Integer id) {
		return commentService.getListCommentByProductId(id);
	}
	
	@PostMapping("/form")
	public CommentModel create(@RequestBody CommentModel commentModel) {
		return commentService.createComment(commentModel);
	}
	
	@GetMapping("/pending")
	public List<Comment> getListCommentPending(){
		return commentService.getListCommentPending();
	}
	
	@GetMapping("/pending/{id}")
	public Comment getCommentByCommentId(@PathVariable("id") Integer id) {
		return commentService.getCommentByCommentId(id);
	}
	
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		commentService.approveComment(id);
	}
	
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		commentService.delete(id);
	}
	
	@GetMapping("/approved")
	public List<Comment> getListCommentChecked(){
		return commentService.getListCommentChecked();
	}
}
