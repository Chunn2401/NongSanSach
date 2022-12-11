
package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class chua thong tin Discount
 * 
 * @author tuan-pm
 * @version 1.00
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Discount")
public class Discount implements Serializable {
	// Thong tin category id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// Thong tin ten ma giam gia
	private String name;
	
	// Thong tin ma giam gia
	private String code;
	
	// Thong tin so tien giam
	private int price;
	
	// Thong tin so luong ma
	private int quality;
	
	// Thong tin ngay ap dung
	private String Applyday;
	
	// Thong tin ngay het han
	private String expiration;
	
	// Thong tin so tien ap dung
	private int Moneylimit;

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
	
	@OneToMany(mappedBy = "discount")
	List<Order> listOrder;

}
