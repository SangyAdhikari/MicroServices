package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.Model.UserAccount;

import java.util.List;

public interface AuthenticationService {

    public UserAccount saveUser(UserAccount user) throws UserAlreadyExistsException ;
    public UserAccount updateUser(UserAccount result, String emailId ) ;
    public UserAccount findByuserEmail( String userEmail);
    List<UserAccount> getUsers();

    public String updatePassword(UserAccount authReq) throws UserNotFoundException;

    public UserAccount getByuserEmail(String userEmail) throws UserNotFoundException;
}

