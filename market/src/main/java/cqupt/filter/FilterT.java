package cqupt.filter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterT {
	@Bean
	 public FilterRegistrationBean someFilterRegistration() {
	        FilterRegistrationBean registration = new FilterRegistrationBean();
	        registration.setFilter(new MarketFilter());
	        registration.addUrlPatterns("/orders/*");
	        registration.addUrlPatterns("/goods/*");
	        registration.addUrlPatterns("/total/*");
	        registration.addUrlPatterns("/orders.html");
	        registration.addUrlPatterns("/goods.html");
	        registration.addUrlPatterns("/total.html");
	        registration.addInitParameter("paramName", "paramValue");
	        registration.setName("sessionFilter");
	        return registration;
	   }
}
