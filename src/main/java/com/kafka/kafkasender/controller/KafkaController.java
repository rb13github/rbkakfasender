package com.kafka.kafkasender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.kafkareceiver.model.Greeting;
import com.kafka.kafkasender.service.KafkaSender;

@RestController
@RequestMapping(value = "/kafka/")
public class KafkaController {

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaSender.sendcontrol(message);

		return "Message sent to the Kafka Topic zenith_product_demo_topic Successfully"+" message conent="+message;
	}
	
	@PostMapping(value = "/objectproducer")
	public String producerobject( @RequestBody Greeting message) {
		kafkaSender.sendObject(message);

		return "Message sent to the Kafka Topic zenith_product_demo_objecttopic Successfully"+" message conent="+message;
	}

}


//C:\kafka_2.12-2.7.0\bin\windows
//
//kafka-server-start.bat .\config\server.properties
//http://localhost:8080//kafka/producer?message=hello to kafka 

//kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic zenith_product_demo_topic