package dev.aman.jobportalauthservice.Services;

import dev.aman.jobportalauthservice.Models.Token;
import dev.aman.jobportalauthservice.Models.User;
import dev.aman.jobportalauthservice.Repositories.TokenRepository;
import dev.aman.jobportalauthservice.Repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class SelfAuthService implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    public SelfAuthService(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token userLogin(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new RuntimeException("User with" + email + " not found. Please signUp to proceed.");
        }
        //Need to compare stored and entered password using matches method
        if(bCryptPasswordEncoder.matches(password, user.get().getHashedPassword())){
            //Generate Token
            Token token = createToken(user.get());
            //Before returning we need to save token
            Token savedToken = tokenRepository.save(token);
            //Returning savedToken as it will have Id
            return savedToken;
        }
        return null;
    }
    @Override
    public User userSignup(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        //We need not store password directly
        //We need to encrypt the password using BCrypt Algo
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }
    @Override
    public void userLogout(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token,
                false, new Date());
        if(optionalToken.isEmpty()){
            throw new RuntimeException("User not found");
        }
        //Getting the token object
        Token storedToken = optionalToken.get();
        //Putting the token deleted value as true
        storedToken.setDeleted(true);
        //Saving token in DB
        tokenRepository.save(storedToken);
        //Here we are saving even after updating else the changes won't be reflected back
    }
    @Override
    public User validateToken(String token) {
        //First need to check whether token is there in DB or not
        //Expiry time should be within defined time limit
        //Checking token with given value, deleted as false and expiry time
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeletedAndExpiryAtGreaterThan(token,
                false, new Date());
        if(optionalToken.isEmpty()){
            return null;
        }
        //Getting users from token
        return optionalToken.get().getUsers();
    }
    //Method to generate Token
    private Token createToken(User user) {
        Token token = new Token();
        token.setUsers(user);
        //We are creating random token
        token.setValue(RandomStringUtils.randomAlphanumeric(128));
        //Setting expiry time for 30 Days from generation
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAfter = today.plusDays(30);
        Date expiryAt = Date.from(thirtyDaysAfter.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(expiryAt);
        return token;
    }
}
