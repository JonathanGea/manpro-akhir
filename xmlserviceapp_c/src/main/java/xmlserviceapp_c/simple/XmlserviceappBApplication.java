package xmlserviceapp_c.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import xmlserviceapp_c.simple.utils.XmlParser;

@SpringBootApplication
@EnableEurekaClient
public class XmlserviceappBApplication {

    public static void main(String[] args) {
      SpringApplication.run(XmlserviceappBApplication.class, args);
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParser();
    }
    
}
