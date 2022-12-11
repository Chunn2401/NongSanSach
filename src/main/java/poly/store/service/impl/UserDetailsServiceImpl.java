package poly.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import poly.store.service.RoleService;
import poly.store.service.UserService;

import org.springframework.security.core.userdetails.User;

/**
 * Class dung de phan quyen cho project
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	// Thong tin user service;
	@Autowired
	private UserService userService;
	
	// Thong tin role service
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	/**
	 * Cung cap quyen cho project
	 * 
	 * @param username
	 * @return userDetails
	 * @exception UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		poly.store.entity.User appUser = this.userService.findUserByEmail(username);
		if(appUser == null) {
			System.out.println("User not found! "+username);
			throw new UsernameNotFoundException("User " + username + " was not found in database");		
		}
		else {
			System.out.println("User found! "+username);
			System.out.println("Password: " + appUser.getPassword());
		}
		
		List<String> roleNames = this.roleService.getRoleNames(appUser.getId());
		List<GrantedAuthority> grandList = new ArrayList<GrantedAuthority>();
		
		if(roleNames!=null) {
			for(String role: roleNames) {
				System.out.println(role);
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grandList.add(authority);
			}
		}
		String password = pe.encode(appUser.getPassword());
		UserDetails userDetails = (UserDetails) new User(appUser.getEmail(), password, grandList);
		
		return userDetails;
	}

}
