package com.stackroute.authenticationservice.Controller;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.Model.UserAccount;
import com.stackroute.authenticationservice.Service.AuthenticationServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
@SpringBootTest(classes = {ControllerTest.class})

public class ControllerTest {
    @Mock
   AuthenticationServiceImpl authenticationService;
    @InjectMocks
  AuthenticationController authenticationController;
    @Test
    public void test_getUsers() {
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("rakesh@gmail.com", "password", "user"));
        userAccounts.add(new UserAccount("abhi@gmail.com", "password", "user"));
        userAccounts.add(new UserAccount("alice@gmail.com", "password", "user"));
        userAccounts.add(new UserAccount("ajay@gmail.com", "password", "user"));
        try {
            Mockito.when(authenticationService.getUsers()).thenReturn(userAccounts);
            assertEquals(4, authenticationController.find(String.valueOf(userAccounts)));
        }
        catch (Exception e ){

        }
    }

    @Test
    public  void test_findByuserEmail(){
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationService.findByuserEmail("rakesh@gmail.com")).thenReturn((userAccount));
            assertEquals(userAccount, authenticationController.find(String.valueOf(userAccount)));
        }
        catch (Exception e ){

        }
    }

    @Test
    public  void test_getByuserEmail() throws UserNotFoundException {
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationService.findByuserEmail("rakesh@gmail.com")).thenReturn((userAccount));
            assertEquals(userAccount, authenticationController.getuserByEmail("rakesh@gmail.com"));
        }
        catch (Exception e ){

        }
    }
    @Test
    public void test_saveUser() throws UserAlreadyExistsException {
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationService.saveUser(userAccount)).thenReturn(userAccount);
            assertEquals(userAccount, authenticationController.saveUser(userAccount));
        }
        catch (Exception e){
        }
    }
    @Test
    public void test_updateUser(){
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationService.findByuserEmail(userAccount.getUserEmail())).thenReturn((userAccount));
            assertEquals(userAccount, authenticationController.find(String.valueOf(userAccount)));
        }
        catch (Exception e){
        }
    }
    @Test
    public void test_updatePassword() throws UserNotFoundException{
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationService.findByuserEmail(userAccount.getUserEmail())).thenReturn((userAccount));
            assertEquals(userAccount, authenticationController.saveUser(userAccount));
        }
        catch (Exception e){
        }
    }

}

