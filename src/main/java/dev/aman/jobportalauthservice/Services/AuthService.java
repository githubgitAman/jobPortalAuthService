package dev.aman.jobportalauthservice.Services;

import dev.aman.jobportalauthservice.Models.Token;
import dev.aman.jobportalauthservice.Models.User;

public interface AuthService {
    Token userLogin(String email, String password);
    User userSignup(String name, String email, String password);
    void userLogout(String token);
    User validateToken(String token);
}
