package cqupt.hq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaServer
@SpringBootApplication
@EnableScheduling
public class EurekaMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaMarketApplication.class, args);
	}
}
