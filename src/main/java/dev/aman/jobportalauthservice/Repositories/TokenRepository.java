package dev.aman.jobportalauthservice.Repositories;

import dev.aman.jobportalauthservice.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    //Checking token Value, Deleted status and Expiry time
    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String value, Boolean deleted, Date currentTime);
}
