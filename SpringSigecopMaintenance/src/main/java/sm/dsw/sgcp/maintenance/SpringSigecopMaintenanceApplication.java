package sm.dsw.sgcp.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringSigecopMaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSigecopMaintenanceApplication.class, args);
	}

}
