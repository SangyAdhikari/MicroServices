package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.Model.UserAccount;
import com.stackroute.authenticationservice.Repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private AuthenticationRepository authenticationRepository;
    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserAccount findByuserEmail( String userEmail) {
        UserAccount userAccount = authenticationRepository.findById(userEmail).get();
        if (userAccount==null);
        return userAccount;
    }

    @Override
    public UserAccount saveUser(UserAccount user) throws UserAlreadyExistsException {
        if(authenticationRepository.existsById(user.getUserEmail())) {
            throw new UserAlreadyExistsException("User already exist");
        }
        return authenticationRepository.save(user);
    }

    @Override
    public String updatePassword(UserAccount authReq ) throws UserNotFoundException {

            UserAccount user = authenticationRepository.findById(authReq.getUserEmail()).get();
            if(user==null)
        {
          throw new UserNotFoundException("Invalid User");
          }
            user.setUserPassword(authReq.getUserPassword());
            authenticationRepository.save(user);
            return "Password updated successfully";
        }


    @Override
    public UserAccount updateUser(UserAccount result, String emailId) {
        if(authenticationRepository.findById(emailId).isPresent()){
            UserAccount user = authenticationRepository.findById(emailId).get();
            user.setUserPassword(result.getUserPassword());
            return authenticationRepository.save(user);
        }
        return null;
    }

    @Override
    public List<UserAccount> getUsers() {
        return authenticationRepository.findAll();
    }

    @Override
    public UserAccount getByuserEmail(String userEmail) throws UserNotFoundException {
    UserAccount user = authenticationRepository.findById(userEmail).get();
    if(user==null){
        throw new UserNotFoundException("User is invalid");
    }
        return user;
    }


}
