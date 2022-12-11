package poly.store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment {
	// Thong tin comment id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;
	private int star;
	private String date;
	private String status;

	// Thong tin nguoi dung
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;

	// Thong tin san pham
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	Product product;
}
