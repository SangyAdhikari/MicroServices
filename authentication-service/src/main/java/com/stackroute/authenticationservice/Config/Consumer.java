package com.stackroute.authenticationservice.Config;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.Model.UserAccount;
import com.stackroute.authenticationservice.Service.AuthenticationService;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer {
    @Autowired
    private AuthenticationService authenticationService;



        @RabbitListener(queues = "Register-User" )
        public void getRabbitMqdto(RabbitMQDto dto) throws UserAlreadyExistsException {

            System.out.println(dto.toString());
        UserAccount user = new UserAccount();
        user.setUserEmail(dto.getEmailId());
        user.setUserType(dto.getUserType());
        user.setUserPassword(dto.getPassword());
        authenticationService.saveUser(user);
        }

    @RabbitListener(queues = "Register-Organizer" )
    public void getRabbitMqdtoConfig(RabbitMQDto dto) throws UserAlreadyExistsException {
        UserAccount user = new UserAccount();
        user.setUserEmail(dto.getEmailId());
        user.setUserType(dto.getUserType());
        user.setUserPassword(dto.getPassword());
        authenticationService.saveUser(user);
    }

    @RabbitListener(queues = "Update-User-Password")
    public void updatePassword(RabbitMQDto dto) throws UserNotFoundException {
            System.out.println(dto.toString());
        UserAccount user = new UserAccount();
        user.setUserEmail(dto.getEmailId());
        user.setUserType(dto.getUserType());
            user.setUserPassword(dto.getPassword());
            authenticationService.updatePassword(user);
    }

    @RabbitListener(queues = "Update-Organizer-Password")
    public void updatePassw(RabbitMQDto dto) throws UserNotFoundException {
        System.out.println(dto.toString());
        UserAccount user = new UserAccount();
        user.setUserEmail(dto.getEmailId());
        user.setUserType(dto.getUserType());
        user.setUserPassword(dto.getPassword());
        authenticationService.updatePassword(user);
    }
}
