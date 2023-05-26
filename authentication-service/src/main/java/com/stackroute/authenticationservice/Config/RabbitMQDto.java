package com.stackroute.authenticationservice.Config;

public class RabbitMQDto {
    private String emailId;
    private String password;
    private String userType;
    public String getEmailId() {
        return emailId;
    }
    public RabbitMQDto setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public RabbitMQDto setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

}

