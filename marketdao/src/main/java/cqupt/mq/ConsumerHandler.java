package cqupt.mq;

public class ConsumerHandler {  
    public void handleMessage(String text) {  
        System.out.println("Received--------------------------: " + text);  
    }  
}  