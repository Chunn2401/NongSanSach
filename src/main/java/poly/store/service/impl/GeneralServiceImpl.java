package poly.store.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import poly.store.dao.EmployeeDao;
import poly.store.dao.RoleDao;
import poly.store.dao.UserDao;
import poly.store.dao.UserRoleDao;
import poly.store.entity.Employee;
import poly.store.entity.Role;
import poly.store.entity.User;
import poly.store.entity.UserRole;
import poly.store.model.EmployeeForm;
import poly.store.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class GeneralServiceImpl implements GeneralService {
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	UserRoleDao userRoleDao;

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public EmployeeForm createEmployee(EmployeeForm employeeForm) {
		// Them user
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = new User();
		user.setEmail(employeeForm.getEmail());
		user.setPassword("1234567");
		user.setFullname(employeeForm.getFullname());
		user.setCreateday(timestamp.toString());
		userDao.save(user);

		// Tim thong tin role theo roleId
		Role role = roleDao.findById(employeeForm.getRole()).get();

		// Them moi mot user co vai tro la ROLE_USER
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);

		// Them moi mot employee
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User temp = userDao.findUserByEmail(username);

		Employee employee = new Employee();
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setCreateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersoncreate(temp.getId());
		employeeDao.save(employee);

		return employeeForm;
	}

	@Override
	public EmployeeForm getOneUserById(Integer id) {
		User user = userDao.findById(id).get();
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setFullname(user.getFullname());
		employeeForm.setEmail(user.getEmail());

		for (Employee employee : user.getListEmployee()) {
			employeeForm.setDepartment(employee.getDepartment());
			employeeForm.setPhone(employee.getPhone());
			employeeForm.setSalary(employee.getSalary());
			employeeForm.setPosition(employee.getPosition());
			employeeForm.setStartday(employee.getStartday());
		}

		for (UserRole userRole : user.getListUserRole()) {
			employeeForm.setRole(userRole.getRole().getId());
		}

		return employeeForm;
	}

	@Override
	public EmployeeForm updateEmployee(EmployeeForm employeeForm) {
		// Cap nhat user
		User user = userDao.findById(employeeForm.getId()).get();
		user.setEmail(employeeForm.getEmail());
		user.setFullname(employeeForm.getFullname());
		userDao.save(user);

		// Cap nhat quyen user
		UserRole userRole = roleDao.getRoleByUserId(employeeForm.getId());
		Role role = roleDao.findById(employeeForm.getRole()).get();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);
		
		// Cap nhat employee
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		
		Employee employee = employeeDao.getEmployeeByUserId(employeeForm.getId());
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setUpdateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersonupdate(temp.getId());
		employeeDao.save(employee);
		
		return employeeForm;
	}

}
