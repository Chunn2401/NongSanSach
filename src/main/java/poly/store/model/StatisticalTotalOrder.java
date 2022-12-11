package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalTotalOrder {
	private long orderSuccess;
	private long orderCancel;
	private long orderWait;
	private long orderTransport;
}
