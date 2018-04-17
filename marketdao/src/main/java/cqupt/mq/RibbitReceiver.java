package cqupt.mq;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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
public class RibbitReceiver {
 
	 @Bean(value="cachingConnectionFactory")
	 public CachingConnectionFactory connectionFactory(){
			CachingConnectionFactory ccf=new CachingConnectionFactory();
			ccf.setAddresses("10.4.128.54:5672");  
			ccf.setUsername("hq");  
			ccf.setPassword("431424"); 
			ccf.setVirtualHost("/"); 
			ccf.setPublisherConfirms(true);  
	     return ccf;
	}
//	 //创建队列
//	 @Bean
//	 public Queue queueMessage() {
//	  return new Queue("market");
//	 }
//	 
//	 @Bean
//	 public Queue goodsQueue() {
//	  return new Queue("goodsQueue");
//	 }
//	 //创建交换器
//	 @Bean
//	 TopicExchange exchange() {
//	  return new TopicExchange("marketExchange");
//	 }
//	 
//	 @Bean
//	 TopicExchange goodsExchange() {
//	  return new TopicExchange("goodsExchange");
//	 }
	//对列绑定并关联到ROUTINGKEY
//	 @Bean
//	 Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
//	  return BindingBuilder.bind(queueMessage).to(exchange).with("marketkey");
//	 }
	 
//	 @Bean
//	 Binding bindingExchangeMessages(Queue goodsQueue, TopicExchange goodsExchange) {//绑定queueMessages和exchange
//	  return BindingBuilder.bind(goodsQueue).to(goodsExchange).with("goods");
//	 }
//	 
//	 @RabbitListener(queues = "market"/*,containerFactory="simpleRabbitListenerContainerFactory"*/)
//	 public void process(String message) {
//		  System.out.println("Topic Receiver1 : " + message);
//		  try {
////			  File file=new File("D:/market.txt");
////			  if(!file.exists()){
////			       file.createNewFile();
////			  }
//			  FileWriter fileWritter = new FileWriter("D:/market.txt",true);//末尾建立
////			  BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
////	          bufferWritter.write(message);
////	          bufferWritter.flush();
////	          bufferWritter.close();
////			  fileWritter.write(message.getBytes());
//			  fileWritter.write(message+"\r\n");
//			  fileWritter.flush();
//			  fileWritter.close();
//		  } catch (IOException e) {
//				e.printStackTrace();
//			}
//	 }
	 @RabbitListener(bindings = @QueueBinding(
	 value = @Queue(value = "marketQueue", durable = "true"),
     exchange = @Exchange(value = "marketExchange", ignoreDeclarationExceptions = "true"),
	 key = "marketkey"))
//	 @RabbitListener(queues = "marketQueue",containerFactory="cachingConnectionFactory")
	 public void process1(String message) {
		 System.out.println("Topic Receiver1 : " + message);
		  try {
//			  File file=new File("D:/market.txt");
//			  if(!file.exists()){
//			       file.createNewFile();
//			  }
			  FileWriter fileWritter = new FileWriter("D:/market.txt",true);//末尾建立
//			  BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
//	          bufferWritter.write(message);
//	          bufferWritter.flush();
//	          bufferWritter.close();
//			  fileWritter.write(message.getBytes());
			  fileWritter.write(message+"\r\n");
			  fileWritter.flush();
			  fileWritter.close();
		  } catch (IOException e) {
				e.printStackTrace();
			}
	 }
 
}
