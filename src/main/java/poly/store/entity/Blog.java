package poly.store.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Blogs")
public class Blog implements Serializable{
	// Thong tin category id
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		// thông tin tiêu đề blog shop
		private String title;
		//thông tin thời gian đăng
		@Temporal(TemporalType.DATE)
		@Column(name = "time")
		private Date time = new Date();
		/* private String time; */
		//thông tin ảnh 1 ;
		private String image1;
		//thông tin nội dung 1;
		private String content1;
		//thông tin ảnh 2
		private String image2;
		//thông tin nội dung 2;
		private String content2;
		//thông tin ảnh 3
		private String image3;
		//thông tin nội dung 3;
		private String content3;
		//thông tin ảnh 4
		private String image4;
		//thông tin nội dung 4;
		private String content4;
		
		//thông tin bình luận blogs shop
		private String comment;
		//thông tin số lượt like của blogs shop
//		private int like;
		// thông tin trạng thái duyệt bài viết
		/* private boolean status; */
				
		
}
