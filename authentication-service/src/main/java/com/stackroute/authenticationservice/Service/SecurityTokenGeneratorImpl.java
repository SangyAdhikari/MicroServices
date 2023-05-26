package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.Model.UserAccount;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(UserAccount user) {
        String jwtToken;
        jwtToken = Jwts.builder().setSubject(user.getUserEmail()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        Map<String, String> map = new HashMap<>();
        map.put("jwtToken", jwtToken);
        map.put("message", "login successful");
        return map;
    }
}
