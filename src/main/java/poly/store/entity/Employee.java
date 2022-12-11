
package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class chua thong tin Employee
 * 
 * @author khoa-ph
 * @version 1.00
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Employees")
public class Employee implements Serializable{
	// Thong tin employee id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// Thong tin phong ban
	private String department;
	
	// Thong tin vi tri
	private String position;

	// Thong tin so dien thoai
	private String phone;
	
	// Thong tin ngay bat dau lam viec
	private String Startday;
	
	// Thong tin luong
	private int salary;
	
	// Thong tin ngay tao 
	private String Createday;
	
	// Thong tin ma nguoi tao
	private int Personcreate;
	
	// Thong tin ngay xoa
	private String Deleteday;
	
	// Thong tin nguoi xoa
	private int Persondelete;
	
	// Thong tin ngay cap nhat
	private String Updateday;
	
	// Thong tin ma nguoi cap nhat
	private int Personupdate;
	
	// Thong tin anh dai dien
	private String image;
	
	// Thong tin nguoi dung
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;
	
}
