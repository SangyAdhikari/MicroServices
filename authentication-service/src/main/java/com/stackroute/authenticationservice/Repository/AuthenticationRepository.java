package com.stackroute.authenticationservice.Repository;

import com.stackroute.authenticationservice.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserAccount, String> {

    }
