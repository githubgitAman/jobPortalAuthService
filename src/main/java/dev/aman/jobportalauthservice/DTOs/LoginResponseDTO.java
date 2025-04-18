package dev.aman.jobportalauthservice.DTOs;

import dev.aman.jobportalauthservice.Models.Token;
import lombok.Data;

@Data
public class LoginResponseDTO {
    //We will share only token as String not complete token
    private String token;
    private ResponseStatus responseStatus;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
