package discoveryserviceapp.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryserviceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryserviceAppApplication.class, args);
//        new SpringApplicationBuilder(DiscoveryserviceAppApplication.class).web(true).run(args);
    }

}
