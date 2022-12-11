
package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class thong tin truy van cua bang Employee
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class EmployeeModel {
	// Thong tin ho va ten
	@Id
	private String Fullname;
	
	// Thong tin phong ban
	private String department;

	// Thong tin so dien thoai
	private String phone;
	
	// Thong tin chuc vu
	private String position;
	
	// Thong tin ngay sinh
	private String birthday;
	
	// Thong tin ngay tham gia
	private String Startday;
	
	// Thong tin luong
	private int salary;
	
	// Thong tin vai tro
//	private int role;
	
}
