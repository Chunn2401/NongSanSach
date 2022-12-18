
package poly.store.service;

import java.util.List;

import poly.store.entity.User;
import poly.store.model.ChangePassModel;
import poly.store.model.InformationModel;

/**
 * Class cung cap cac dich vu thao tac voi table Users trong database
 * 
 * @author KHOA-PH
 * @version 1.00
 */
public interface UserService {

	/**
	 * Tim kiem User bang email
	 * 
	 * @param username thong tin email
	 * @return User tim duoc
	 */
	User findUserByEmail(String email);

	void save(User user);

	List<User> findAll();

	User create(User user);

	List<User> findAllUserAnable();

	List<User> findAllUsers();

	InformationModel getUserAccount();

	InformationModel update(InformationModel informationModel);

	ChangePassModel updatePass(ChangePassModel changePassModel);

	void delete(Integer id);

}
