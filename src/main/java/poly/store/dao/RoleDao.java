
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.Role;
import poly.store.entity.UserRole;

/**
 * Class thuc hien truy van thong tin bang Role trong database
 * 
 * @author khoa-ph
 * @version 1.00
 */
public interface RoleDao extends JpaRepository<Role, Integer>{
	@Query("SELECT u.role.name FROM UserRole u WHERE u.user.id = :uid")
	List<String> getRoleNames(@Param("uid") Integer id);
	
	@Query("SELECT u FROM UserRole u WHERE u.user.id = :uid")
	UserRole getRoleByUserId(@Param("uid") Integer id);
}
