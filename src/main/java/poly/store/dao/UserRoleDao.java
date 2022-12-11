
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.UserRole;

/**
 * Class thuc hien truy van thong tin bang User_Role trong database
 * 
 * @author khoa-ph
 * @version 1.00
 */
public interface UserRoleDao extends JpaRepository<UserRole, Integer>{
	@Query("SELECT u FROM UserRole u WHERE (u.role.id = 2 or u.role.id = 3) and u.user.Deleteday = null")
	List<UserRole> findAllAdminOrDirector();
}
 