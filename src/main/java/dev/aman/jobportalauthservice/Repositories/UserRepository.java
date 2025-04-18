package dev.aman.jobportalauthservice.Repositories;

import dev.aman.jobportalauthservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Our output User will have Id associate with it whereas for input User will not have
    User save(User user);
    Optional<User> findByEmail(String email);
}
