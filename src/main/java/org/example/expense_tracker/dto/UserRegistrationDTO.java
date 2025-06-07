package org.example.expense_tracker.dto;

public class UserRegistrationDTO {
    private String username;
    private String password;

    public UserRegistrationDTO(){}

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
