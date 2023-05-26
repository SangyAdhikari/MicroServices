package com.stackroute.bookingticketservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "BookingTicket - Microservice",description = "Booking Ticket Micro Service API Calls"))
public class BookingTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingTicketServiceApplication.class, args);
	}

}
