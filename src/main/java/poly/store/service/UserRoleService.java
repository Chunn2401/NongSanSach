
package poly.store.service;

import java.util.List;

import poly.store.entity.UserRole;

/**
 * Class cung cap cac dich vu thao tac voi table User_Role trong database
 * 
 * @author KHOA-PH
 * @version 1.00
 */
public interface UserRoleService {

	/**
	 * Luu user role vao database
	 * 
	 * @param userRole
	 */
	void save(UserRole userRole);

	List<UserRole> findAll();

	List<UserRole> findAllAdminOrDirector();

	void delete(Integer id);

}
