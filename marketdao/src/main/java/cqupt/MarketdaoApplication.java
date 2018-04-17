package cqupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient//不需要feign,因为是别人连接
@EnableCaching
public class MarketdaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarketdaoApplication.class, args);
	} 

}
