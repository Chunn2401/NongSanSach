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

/**
 * Class chua thong tin User
 * 
 * @author khoa-ph
 * @version 1.00
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	// Thong tin id san pham
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Thong tin ma san pham
	private String code;

	// Thong tin ten san pham
	private String name;

	// Thong tin gia san pham
	private int price;

	// Thong tin diem doi qua
	private int point;

	// Thong tin so luong san pham
	private int quality;

	// Thong tin so luot xem
	private int views;

	// Mo ta san pham
	private String description;

	// Thong tin cac thong so
	private String specification;

	// Thong tin hinh anh 1
	private String image1;

	// Thong tin hinh anh 2
	private String image2;

	// Thong tin hinh anh 3
	private String image3;

	// Thong tin hinh anh 4
	private String image4;

	// Thong tin hinh anh 5
	private String image5;

	// Hien thi san pham hay khong
	private boolean active;
	
	// Hien thi ten dung de tim kiem
	private String Namesearch;
	
	// Hien thi mau sac san pham neu co
	private String color;
	
	// Hien thi dung luong san pham neu co
	private String memory;
	
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

	// Thong tin nha san xuat
	@ManyToOne
	@JoinColumn(name = "Manu_Id")
	Manufacturer manufacturer;

	// Thong tin danh muc
	@ManyToOne
	@JoinColumn(name = "Cate_Id")
	Category category;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	List<Order> listOrder;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	List<Favorite> listFavorite;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	List<Comment> listComment;
}
