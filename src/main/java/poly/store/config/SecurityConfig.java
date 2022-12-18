
package poly.store.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import poly.store.service.UserService;
import poly.store.service.impl.UserDetailsServiceImpl;

/**
 * Class dung de phan quyen cho project
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Thong tin User Service
	@Autowired
	UserService userService;

	// Phuong thuc ma hoa mat khau
	@Autowired
	BCryptPasswordEncoder pe;

	// Phuong thuc cap quyen
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// Thong tin datasource
	@Autowired
	DataSource dataSource;

	/**
	 * Cung cap quyen cho project
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * Xu ly phan quyen nguoi dung
	 * 
	 * @param http
	 * @throws exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// Cac trang yeu cau quyen su dung la Admin hoac Director
		http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_DIRECTOR')");

		http.authorizeRequests()
				.antMatchers("/shop/profile/**", "/shop/favorite/**", "/shop/cart/checkout", "/account", "/account/**",
						"/rest/favorite/add/**")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_DIRECTOR')");

		// Các trang không yêu cầu login
		http.authorizeRequests().anyRequest().permitAll();

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403page");

		// Cau hinh cho form login
		http.authorizeRequests().and().formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").failureForwardUrl("/login").defaultSuccessUrl("/login/success", false);

		// Cau hinh dang xuat khoi chuong trinh
		http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/home");

		// Cau hinh remember me
		http.authorizeRequests().and().rememberMe().tokenValiditySeconds(86400);
	}

	/**
	 * Cung cap phuong thuc ma hoa
	 * 
	 * @return phuong thuc ma hoa
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
