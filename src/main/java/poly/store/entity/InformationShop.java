
package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class chua thong tin cua hang
 * 
 * @author tuan-pm
 * @version 1.00
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Informationshop")
public class InformationShop implements Serializable {
	// Thong tin category id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// Thong tin ten shop
	private String name;
	
	// Thong tin trang thai kich hoat
	private boolean active;
	
	// Thong tin thoi gian mo cua
	private String Timeopen;

	// Thong tin logo
	private String logo;

	// Thong tin so dien thoai
	private String phone;

	// Thong tin tax
	private String fax;

	// Thong tin email
	private String email;

	// Thong tin logo footer
	private String Logofooter;

	// Thong tin dia chi shop
	private String address;

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
}
