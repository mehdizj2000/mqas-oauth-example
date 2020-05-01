package au.com.jaycar.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MqasClientApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(MqasClientApiApplication.class, args);
    }

}
