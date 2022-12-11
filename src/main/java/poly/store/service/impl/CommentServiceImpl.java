package poly.store.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CommentDao;
import poly.store.dao.ProductDao;
import poly.store.dao.UserDao;
import poly.store.entity.Comment;
import poly.store.entity.Product;
import poly.store.entity.User;
import poly.store.model.CommentModel;
import poly.store.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ProductDao productDao;

	@Override
	public List<Comment> getListCommentByProductId(Integer id) {
		return commentDao.getListCommentByProductId(id);
	}

	@Override
	public CommentModel createComment(CommentModel commentModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userDao.findUserByEmail(username);

		Product product = productDao.findById(commentModel.getProductId()).get();

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		Comment comment = new Comment();

		comment.setContent(commentModel.getContent());
		comment.setStar(commentModel.getStar());
		comment.setDate(strDate);
		comment.setProduct(product);
		comment.setUser(temp);
		comment.setStatus("0");
		commentDao.save(comment);

		return commentModel;
	}

	@Override
	public List<Comment> getListCommentPending() {
		return commentDao.getListCommentPending();
	}

	@Override
	public Comment getCommentByCommentId(Integer id) {
		return commentDao.getCommentByCommentId(id);
	}

	@Override
	public void approveComment(Integer id) {
		Comment comment = commentDao.findById(id).get();
		comment.setStatus("1");
		commentDao.save(comment);
	}

	@Override
	public void delete(Integer id) {
		Comment comment = commentDao.findById(id).get();
		commentDao.delete(comment);
	}

	@Override
	public List<Comment> getListCommentChecked() {
		return commentDao.getListCommentChecked();
	}

	@Override
	public int getCountCommentByProductNameSearch(String nameSearch) {
		return commentDao.getCountCommentByProductNameSearch(nameSearch);
	}

	@Override
	public int getAllStarCommentByProductNameSearch(String nameSearch) {
		int result = 0;
		int totalStar = 0;
		List<Integer> listStar = commentDao.getAllStarCommentByProductNameSearch(nameSearch);
		if(listStar.isEmpty() == false) {
			for(int i = 0; i < listStar.size(); i++) {
				totalStar = totalStar + listStar.get(i);
				System.out.println(totalStar);
			}
			result = Math.round(totalStar / (listStar.size()));
		}
		return result;
	}

}
