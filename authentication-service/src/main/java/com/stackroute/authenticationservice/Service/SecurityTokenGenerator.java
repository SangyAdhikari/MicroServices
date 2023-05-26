package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.Model.UserAccount;

import java.util.Map;

public interface SecurityTokenGenerator {

        Map<String, String> generateToken(UserAccount user  );

    }

