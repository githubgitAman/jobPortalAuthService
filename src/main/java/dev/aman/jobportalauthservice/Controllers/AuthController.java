package dev.aman.jobportalauthservice.Controllers;

import dev.aman.jobportalauthservice.DTOs.*;
import dev.aman.jobportalauthservice.DTOs.ResponseStatus;
import dev.aman.jobportalauthservice.Models.Token;
import dev.aman.jobportalauthservice.Models.User;
import dev.aman.jobportalauthservice.Services.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Login Method
    //Takes a DTO object and will return token
    @PostMapping("/login")
    public LoginResponseDTO userLogin(@RequestBody LoginRequestDTO loginRequestDto){
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        try{
            //Providing only String value of token back to user
            Token token = authService.userLogin(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            loginResponseDTO.setToken(token.getValue());
            loginResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            loginResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return loginResponseDTO;
    }
    //Signup Method
    //Will take DTO object and will return UserDTO object
    @PostMapping("/signup")
    public UserDTO userSignUp(@RequestBody SignupRequestDTO signupRequestDto){
        User user = authService.userSignup(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());
        //Calling static from method created in UserDTO
        return UserDTO.from(user);
    }
    //Logout Method
    //Will take token to validate and will return message
    @PatchMapping("/logout")
    public String userLogout(@RequestBody LogoutRequestDTO logoutRequestDto){
        return null;
    }
    //Validate Token Method
    //Will take token and will return UserDTO as we need to validate some extra info like roles etc
    //Returning complete token will make that possible
    @GetMapping("/validate")
    public UserDTO validateToke(String token){
        return null;
    }
}
