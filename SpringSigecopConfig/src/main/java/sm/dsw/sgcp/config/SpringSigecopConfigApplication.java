package sm.dsw.sgcp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringSigecopConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSigecopConfigApplication.class, args);
	}

}
