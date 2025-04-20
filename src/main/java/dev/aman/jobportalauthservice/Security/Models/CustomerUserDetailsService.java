package dev.aman.jobportalauthservice.Security.Models;

import dev.aman.jobportalauthservice.Models.User;
import dev.aman.jobportalauthservice.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        //Convert user object to UserDetails object

        return new CustomUserDetails(user);
    }
}
