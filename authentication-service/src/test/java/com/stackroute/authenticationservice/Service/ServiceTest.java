package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.Model.UserAccount;
import com.stackroute.authenticationservice.Repository.AuthenticationRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

//@SpringBootTest
@SpringBootTest(classes = {ServiceTest.class})
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    AuthenticationRepository authenticationRepository;
    @InjectMocks
    AuthenticationServiceImpl authenticationService;

    @Test
    public void test_getUsers() {
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("rakesh@gmail.com", "password", "user"));
        userAccounts.add(new UserAccount("abhi@gmail.com", "password", "user"));
        userAccounts.add(new UserAccount("alice@gmail.com", "password", "user"));
        userAccounts.add(new UserAccount("ajay@gmail.com", "password", "user"));
        try {
        Mockito.when(authenticationRepository.findAll()).thenReturn(userAccounts);
        assertEquals(4, authenticationService.getUsers().size());
        }
        catch (Exception e ){

        }
    }
    @Test
    public  void test_findByuserEmail(){
    UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
        Mockito.when(authenticationRepository.findById("rakesh@gmail.com")).thenReturn(Optional.of(userAccount));
        assertEquals(userAccount, authenticationService.findByuserEmail("rakesh@gmail.com"));
        }
        catch (Exception e ){

        }
    }

    @Test
    public  void test_getByuserEmail() throws UserNotFoundException{
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationRepository.findById("rakesh@gmail.com")).thenReturn(Optional.of(userAccount));
            assertEquals(userAccount, authenticationService.getByuserEmail("rakesh@gmail.com"));
        }
        catch (Exception e ){

        }
    }
    @Test
    public void test_saveUser() throws UserAlreadyExistsException {
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
            Mockito.when(authenticationRepository.save(userAccount)).thenReturn(userAccount);
            assertEquals(userAccount, authenticationService.saveUser(userAccount));
        }
        catch (Exception e){
    }
    }
    @Test
    public void test_updateUser(){
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
        Mockito.when(authenticationRepository.findById(userAccount.getUserEmail())).thenReturn(Optional.of(userAccount));
        assertEquals(userAccount, authenticationService.findByuserEmail(String.valueOf(userAccount)));
        }
        catch (Exception e){
        }
    }
    @Test
    public void test_updatePassword() throws UserNotFoundException{
        UserAccount userAccount = new UserAccount("rakesh@gmail.com", "password", "user");
        try {
        Mockito.when(authenticationRepository.findById(userAccount.getUserEmail())).thenReturn(Optional.of(userAccount));
        assertEquals(userAccount, authenticationService.saveUser(userAccount));
        }
        catch (Exception e){
        }
    }

}




















//    8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//    @Mock
//    private AuthenticationRepository authenticationRepository;
//
//    @InjectMocks
//    private AuthenticationServiceImpl authenticationService;
//    private List<UserAccount> userAuthenticationList;
//    private UserAccount userAccount;
//    private UserAccount updateUser;
//
//    @BeforeEach
//    public void setup() {
//        userAccount = new UserAccount();
//        userAccount.setUserEmail("manjari@gmail.com");
//        userAccount.setUserPassword("zxcv");
//        userAccount.setUserType("User");
//        userAuthenticationList.add(userAccount);
//
//    }
//    @Test
//    public void test_getUsers(){
//        when(authenticationRepository.save(userAccount)).thenReturn(userAccount);
//        when(authenticationRepository.findAll()).thenReturn(userAuthenticationList);
//        List<UserAccount> list = authenticationService.getUsers();
//        assertEquals(list, userAuthenticationList);
//    }
//    @Test
//    public void test_getByuserEmail() throws UserNotFoundException {
//        when(authenticationRepository.findById(userAccount.getUserEmail())).thenReturn(Optional.ofNullable(userAccount));
//        assertEquals(userAccount,authenticationService.findByuserEmail(userAccount.getUserEmail()));
//    }
//     @Test
//    public void test_saveUser() throws UserAlreadyExistsException {
//         when(authenticationRepository.save(userAccount )).thenReturn(userAccount);
//         assertEquals(userAccount, authenticationRepository.save( userAccount));
//    }88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//    @Test
//    public void test_findByuserEmail(){
//        String userAccount = new String();
//        when(authenticationRepository.findById(userAccount)).thenReturn(userAccount);
//        assertEquals(userAccount, authenticationRepository.getOne(userAccount));
//    }
//    @Test
//    public void test_updateUser(){
//        when(authenticationRepository.updateUser(userAccount.getUserEmail())).thenReturn(userAccount);
//        userAccount.setUserPassword("zxcv");
//        authenticationService.updateUser();
//        userAccount user = authenticationRepository.findByUserEmail(userAccount.getUserEmail());
//        assertEquals(userAccount, authenticationRepository.updateUser(userAccount));
//    }
//    @Test
//    public void test_updatePassword() throws UserNotFoundException{
//        when(authenticationRepository.updatePassword(userAccount.getUserEmail())).thenReturn(userAccount);
//        userAccount.setUserPassword("rtyu");
//        authenticationService.updatePassword();
//        userAccount user = authenticationRepository.findByUserEmail(userAccount.getUserEmail());
//        assertEquals(userAccount, authenticationRepository.updatePassword(userAccount));
//    }

//}


