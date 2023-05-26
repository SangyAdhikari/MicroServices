package com.stackroute.eventdetailsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EventDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDetailsServiceApplication.class, args);
	}

}
