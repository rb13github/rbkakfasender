package com.kafka.kafkasender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.kafkareceiver.model.Greeting;

import org.springframework.kafka.support.SendResult;
@Service
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;
	
	String kafkaTopic = "zenith_product_demo_topic";
	
	@Value(value = "${message.topic.name}")
    private String topicName;
	
	@Value(value = "${message.topic.object.name}")
    private String objectTopicName;
	
	public void send(String message) {
	    
	    kafkaTemplate.send(topicName, message);
	}
	
	
public void sendObject(Greeting message) {
	    System.out.println(" Topic name"+  objectTopicName +"  Greeting object message"+ message);
	    
	 greetingKafkaTemplate.send(objectTopicName, message);
	
	 

}
	
	public void sendcontrol(String message) {
		
	
	 ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

     future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

         @Override
         public void onSuccess(SendResult<String, String> result) {
             System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata()
                 .offset() + "]");
         }

         @Override
         public void onFailure(Throwable ex) {
             System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
         }
     });
	}
}