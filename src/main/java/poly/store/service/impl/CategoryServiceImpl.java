package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.User;
import poly.store.model.CategoryModel;
import poly.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Category category = new Category();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setPersoncreate(temp.getId());
		category.setCreateday(timestamp.toString());
		categoryDao.save(category);
		return categoryModel;
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.getListCategory();
	}

	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Category category = categoryDao.findById(id).get();
		category.setPersondelete(temp.getId());
		category.setDeleteday(timestamp.toString());
		categoryDao.save(category);
	}

	@Override
	public CategoryModel getOneCategoryById(Integer id) {
		Category category = categoryDao.findById(id).get();
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName(category.getName());
		categoryModel.setNameSearch(category.getNamesearch());
		categoryModel.setLogo(category.getLogo());
		categoryModel.setBanner(category.getBanner());
		categoryModel.setDescribe(category.getDescription());
		return categoryModel;
	}

	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Category category = categoryDao.findById(categoryModel.getId()).get();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setUpdateday(timestamp.toString());
		category.setPersonupdate(temp.getId());
		categoryDao.save(category);
		return categoryModel;
	}

	@Override
	public Category getCategoryByNameSearch(String nameSearch) {
		return categoryDao.getCategoryByNameSearch(nameSearch);
	}
}
