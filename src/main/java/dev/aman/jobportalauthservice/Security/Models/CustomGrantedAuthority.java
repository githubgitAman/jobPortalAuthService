package dev.aman.jobportalauthservice.Security.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.aman.jobportalauthservice.Models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;
    public CustomGrantedAuthority(Role role) {
        this.authority = role.getName();
    }
    public CustomGrantedAuthority() {

    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
