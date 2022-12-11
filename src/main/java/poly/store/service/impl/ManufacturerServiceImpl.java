package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.ManufacturerDao;
import poly.store.dao.UserDao;
import poly.store.entity.Manufacturer;
import poly.store.entity.User;
import poly.store.model.ManufacturerModel;
import poly.store.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	@Autowired
	ManufacturerDao manufacturerDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerModel.getName());
		manufacturer.setLogo(manufacturerModel.getLogo());
		manufacturer.setBanner(manufacturerModel.getBanner());
		manufacturer.setDescription(manufacturerModel.getDescribe());
		manufacturer.setPersoncreate(temp.getId());
		manufacturer.setCreateday(timestamp.toString());
		manufacturerDao.save(manufacturer);
		return manufacturerModel;
	}

	@Override
	public List<Manufacturer> findAll() {
		return manufacturerDao.getListManufacturer();
	}

	@Override
	public ManufacturerModel getOneManufacturerById(Integer id) {
		Manufacturer manufacturer = manufacturerDao.findById(id).get();
		ManufacturerModel manufacturerModel = new ManufacturerModel();
		manufacturerModel.setName(manufacturer.getName());
		manufacturerModel.setLogo(manufacturer.getLogo());
		manufacturerModel.setBanner(manufacturer.getBanner());
		manufacturerModel.setDescribe(manufacturer.getDescription());
		return manufacturerModel;
	}

	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Manufacturer manufacturer = manufacturerDao.findById(id).get();
		manufacturer.setPersondelete(temp.getId());
		manufacturer.setDeleteday(timestamp.toString());
		manufacturerDao.save(manufacturer);
	}

	@Override
	public ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);
		
		Manufacturer manufacturer = manufacturerDao.findById(manufacturerModel.getId()).get();
		manufacturer.setName(manufacturerModel.getName());
		manufacturer.setLogo(manufacturerModel.getLogo());
		manufacturer.setBanner(manufacturerModel.getBanner());
		manufacturer.setDescription(manufacturerModel.getDescribe());
		manufacturer.setUpdateday(timestamp.toString());
		manufacturer.setPersonupdate(temp.getId());
		manufacturerDao.save(manufacturer);
		return manufacturerModel;
	}

}
