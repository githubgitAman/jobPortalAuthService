package dev.aman.jobportalauthservice.DTOs;

import dev.aman.jobportalauthservice.Models.Token;
import lombok.Data;

@Data
public class LogoutRequestDTO {
    //We will share only token as String not complete token
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
