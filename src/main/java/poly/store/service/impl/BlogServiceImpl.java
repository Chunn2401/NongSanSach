package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.BlogDao;
import poly.store.entity.Blog;
import poly.store.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	BlogDao blogDao;

	@Override
	public List<Blog> findAll() {
		return blogDao.findAll();
	}

	@Override
	public Blog findById(Integer id) {
		return blogDao.findById(id).get();
	}

	@Override
	public Blog create(Blog blog) {
		return blogDao.save(blog);
	}

	@Override
	public Blog update(Blog blog) {
		return blogDao.save(blog);
	}

	@Override
	public void delete(Integer id) {
		blogDao.deleteById(id);
		
	}

}
