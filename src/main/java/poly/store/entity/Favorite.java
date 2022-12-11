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

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Favorites")
public class Favorite implements Serializable {
	// Thong tin favorite id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String date;

	// Thong tin nguoi dung
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;

	// Thong tin san pham
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	Product product;
}
