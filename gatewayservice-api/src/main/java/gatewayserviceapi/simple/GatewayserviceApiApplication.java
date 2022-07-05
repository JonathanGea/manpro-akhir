package gatewayserviceapi.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserviceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserviceApiApplication.class, args);
	}

}
