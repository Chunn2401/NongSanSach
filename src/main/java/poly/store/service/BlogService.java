package poly.store.service;

import java.util.List;

import poly.store.entity.Blog;

public interface BlogService {
	List<Blog> findAll();
	
	Blog findById(Integer id);
	
	Blog create(Blog blog);

	Blog update(Blog blog);

	void delete(Integer id);
}
