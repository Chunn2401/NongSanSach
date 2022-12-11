package poly.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NongSanSachApplication {

	public static void main(String[] args) {
		SpringApplication.run(NongSanSachApplication.class, args);
	}

}
