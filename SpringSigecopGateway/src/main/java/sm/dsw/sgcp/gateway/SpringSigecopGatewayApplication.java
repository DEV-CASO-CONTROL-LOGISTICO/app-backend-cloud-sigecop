package sm.dsw.sgcp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringSigecopGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSigecopGatewayApplication.class, args);
	}

}
