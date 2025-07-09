package sm.dsw.sgcp.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringSigecopEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSigecopEurekaApplication.class, args);
	}

}
