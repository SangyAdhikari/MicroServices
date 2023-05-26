package com.stackroute.registrationservice.RabbitMQ;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


import com.stackroute.registrationservice.Entity.RegisterOrganizer;
import com.stackroute.registrationservice.Entity.RegisterUser;


@org.springframework.stereotype.Component
public class RabbitMqClass {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	 DirectExchange exchange;
	
	 public RegisterUser userData(RegisterUser registerUser)
	 {
		 RabbitMqMessage message=new RabbitMqMessage();
			message.setEmailId(registerUser.getEmailId()).setPassword(registerUser.getPassword()).setUserType(registerUser.getUserType());
			rabbitTemplate.convertAndSend(exchange.getName(),"Register-User",message);
			return registerUser;
	 }
	 public void updateUserData(String emailId,String password)
	 {
		 RabbitMqMessage message=new RabbitMqMessage();
			message.setEmailId(emailId).setPassword(password);
			rabbitTemplate.convertAndSend(exchange.getName(), "Update-User-Password", message);
	 }
	 public void organizerData(RegisterOrganizer registerOrganizer)
	 {
		 RabbitMqMessage message=new RabbitMqMessage();
			message.setEmailId(registerOrganizer.getEmailId()).setPassword(registerOrganizer.getPassword()).setUserType(registerOrganizer.getUserType());
			System.out.println(message.toString());
			rabbitTemplate.convertAndSend(exchange.getName(), "Register-Organizer", message);
	 }
	 public void updateOrganizerData(String emailId,String password) {
		 RabbitMqMessage message=new RabbitMqMessage();
			message.setEmailId(emailId).setPassword(password);
			rabbitTemplate.convertAndSend(exchange.getName(), "Update-Organizer-Password", message);
	 }

}
