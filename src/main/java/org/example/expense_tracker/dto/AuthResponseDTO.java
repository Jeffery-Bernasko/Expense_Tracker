package org.example.expense_tracker.dto;

public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO(String token){
        this.token = token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
