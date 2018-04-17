package cqupt.mq;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class ConsumerSimpleMessageListenerContainer extends SimpleMessageListenerContainer {  
	  
    public void startConsumers() throws Exception {  
        super.doStart();  
    }  
  
}  