package dev.aman.jobportalauthservice.DTOs;

import dev.aman.jobportalauthservice.Models.Role;
import dev.aman.jobportalauthservice.Models.User;
import lombok.Data;

import java.util.List;

public class UserDTO {
    private String name;
    private String email;
    private List<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public static UserDTO from(User user) {
        //Checking null pointer exception
        if(user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
