package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address implements Serializable{
	// Thong tin address id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// Thong tin ho va ten
	private String Fullname;
	
	// Thong tin so dien thoai
	private String phone;
	
	// Thong tin tinh
	private String province;
	
	// Thong tin huyen
	private String district;
	
	// Thong tin xa
	private String ward;
	
	// Thong tin dia chi chi tiet
	private String detail;
	
	// Thong tin nguoi dung
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
	List<Order> listOrder;
}
