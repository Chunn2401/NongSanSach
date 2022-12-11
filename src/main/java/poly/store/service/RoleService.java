
package poly.store.service;

import java.util.List;

import poly.store.entity.Role;

/**
 * Class cung cap cac dich vu thao tac voi table Roles trong database
 * 
 * @author khoa-ph
 * @version 1.00
 */
public interface RoleService {
	
	/**
	 * Tim role bang role id
	 * 
	 * @param thong tin id cua role
	 * @return Role tim duoc
	 */
	Role findRoleById(int roleId);
	
	/**
	 * Tim ten vai tro dua vao user id
	 * 
	 * @param thong tin user id
	 * @return danh sach ten vai tro tim duoc
	 */
	List<String> getRoleNames(int userId);

}
