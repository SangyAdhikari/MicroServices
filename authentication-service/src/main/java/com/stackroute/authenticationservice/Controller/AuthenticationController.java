package com.stackroute.authenticationservice.Controller;

import com.stackroute.authenticationservice.Exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.Exception.UserNotFoundException;
import com.stackroute.authenticationservice.Model.UserAccount;
import com.stackroute.authenticationservice.Service.AuthenticationService;
import com.stackroute.authenticationservice.Service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/evento/authentication")
public class AuthenticationController {
    private AuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody UserAccount userAccount) {
        Map<String, String> map = null;
        UserAccount user = authenticationService.findByuserEmail(userAccount.getUserEmail());
        System.out.println(user.toString());
        if (user != null && user.getUserPassword().equals(userAccount.getUserPassword())) {
            System.out.println("before generate");
            map = securityTokenGenerator.generateToken(userAccount);
            System.out.println("after generate");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>("check your email id and password", HttpStatus.NOT_FOUND);
    }



    @PostMapping("/register/accountHolders")

    public ResponseEntity<UserAccount> saveUser(@RequestBody UserAccount userAccount) throws UserAlreadyExistsException {
        UserAccount user = authenticationService.saveUser(userAccount);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userEmail}")
    public ResponseEntity<UserAccount> updateAccount(@RequestBody UserAccount userAccount, @PathVariable String userEmail) {
        return new ResponseEntity<>(authenticationService.updateUser(userAccount, userEmail), HttpStatus.ACCEPTED);
    }

    @PostMapping("/authenticate/users")
    public ResponseEntity<UserAccount> authenticate(@RequestBody UserAccount userAccount) throws UserAlreadyExistsException{
        UserAccount user = authenticationService.saveUser(userAccount);
         return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> find(String s) {
        return new ResponseEntity<>(authenticationService.getUsers(), HttpStatus.OK);
    }

    @PutMapping("evento/update")
    public ResponseEntity<String> updatePassword(@RequestBody UserAccount userAccount) throws UserNotFoundException {
        return new ResponseEntity<>(authenticationService.updatePassword(userAccount), HttpStatus.ACCEPTED);
    }

    @GetMapping("evento/getId/{userEmail}")
    public ResponseEntity<UserAccount> getuserByEmail(@PathVariable("userEmail") String userEmail) throws UserNotFoundException {
        return new ResponseEntity<>(authenticationService.getByuserEmail(userEmail),HttpStatus.OK);
    }


}