package poly.store.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
	private String code;
	private Integer id;
	private String name;
	private List<District> districts;
}
