package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDao;
import poly.store.dao.MenuOneDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.MenuOne;
import poly.store.entity.User;
import poly.store.model.Nav1Model;
import poly.store.service.MenuOneService;

@Service
public class MenuOneServiceImpl implements MenuOneService{
	@Autowired
	MenuOneDao menuOneDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public Nav1Model createNav1(Nav1Model nav1Model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Category category = categoryDao.findById(nav1Model.getCategoryId()).get();
		
		MenuOne menuOne = new MenuOne();
		menuOne.setName(nav1Model.getName());
		menuOne.setNamesearch(nav1Model.getNameSearch());
		menuOne.setCategory(category);
		menuOne.setCreateday(timestamp.toString());
		menuOne.setPersoncreate(temp.getId());
		
		menuOneDao.save(menuOne);
		return nav1Model;
	}

	@Override
	public List<MenuOne> findAll() {
		return menuOneDao.getListMenuOne();
	}

	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		MenuOne entity = menuOneDao.findById(id).get();
		entity.setDeleteday(timestamp.toString());
		entity.setPersondelete(temp.getId());
		menuOneDao.save(entity);
	}

	@Override
	public Nav1Model getOneNav1ById(Integer id) {
		Nav1Model nav1Model = new Nav1Model();
		MenuOne entity = menuOneDao.findById(id).get();
		nav1Model.setName(entity.getName());
		nav1Model.setNameSearch(entity.getNamesearch());
		nav1Model.setCategoryId(entity.getCategory().getId());
		return nav1Model;
	}

	@Override
	public Nav1Model updateNav1(Nav1Model nav1Model) {
		Category category = categoryDao.findById(nav1Model.getCategoryId()).get();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		MenuOne entity = menuOneDao.findById(nav1Model.getId()).get();
		entity.setName(nav1Model.getName());
		entity.setNamesearch(nav1Model.getNameSearch());
		entity.setCategory(category);
		entity.setUpdateday(timestamp.toString());
		entity.setPersonupdate(temp.getId());
		menuOneDao.save(entity);
		return nav1Model;
	}

}
