package cqupt.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
@Configuration
public class RabbitSender {
	
	 @Autowired
	 CachingConnectionFactory	connectionFactory;
	 
	 
	 public CachingConnectionFactory connectionFactory(){
			CachingConnectionFactory ccf=new CachingConnectionFactory();
			ccf.setAddresses("10.4.128.54:5672");  
			ccf.setUsername("hq");  
			ccf.setPassword("431424"); 
			ccf.setVirtualHost("/"); 
			ccf.setPublisherConfirms(true);  
	     return ccf;
		}
	
	 @Bean  
	 @Scope(value="prototype")  	
	 public RabbitTemplate rabbitTemplate(){  
	     RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory());  
	     return rabbitTemplate;  
	 }
	
}
