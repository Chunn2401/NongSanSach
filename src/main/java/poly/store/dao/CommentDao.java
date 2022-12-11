package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer>{
	@Query("SELECT c FROM Comment c WHERE c.status = 1 AND c.product.id = :uid")
	List<Comment> getListCommentByProductId(@Param("uid") Integer id);
	
	@Query("SELECT c FROM Comment c WHERE c.status = 0")
	List<Comment> getListCommentPending();
	
	@Query("SELECT c FROM Comment c WHERE c.status = 1")
	List<Comment> getListCommentChecked();
	
	@Query("SELECT c FROM Comment c WHERE c.id = :uid")
	Comment getCommentByCommentId(@Param("uid") Integer id);
	
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.product.Namesearch = :unameSearch")
	int getCountCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
	
	@Query("SELECT c.star FROM Comment c WHERE c.product.Namesearch = :unameSearch")
	List<Integer> getAllStarCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
}
