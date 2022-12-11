package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogsModel {
	private int id;
	private String title;
	private String time;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String content1;
	private String content2;
	private String content3;
	private String content4;
	private String comment;
	private int like;
}
