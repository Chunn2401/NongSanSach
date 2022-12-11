
package poly.store.service;

import java.util.List;

import poly.store.entity.Employee;
import poly.store.model.EmployeeModel;

/**
 * Class cung cap cac dich vu thao tac voi table Employee trong database
 * 
 * @author khoa-ph
 * @version 1.00
 */
public interface EmployeeService {

	List<EmployeeModel> getListEmployee();

	void save(Employee employee);
	
}
