package io.naav97.balance_checker_api.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.naav97.balance_checker_api.repositories.UserRepository;
import io.naav97.balance_checker_api.models.User;

@Service
public class AuthService {

  private final UserRepository user_repo;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  public AuthService(UserRepository ur) {
    this.user_repo = ur;
  }

  public boolean login(String username, String password) {
    Optional<User> user = user_repo.findByUsername(username);
    if (user.isPresent()) {
      return passwordEncoder.matches(password, user.get().get_password());
    }
    return false;
  }

  public User createAccount(String username, String password) {
    if (user_repo.findByUsername(username).isPresent()) {
      throw new RuntimeException("Username already taken");
    }
    User user = new User();
    user.set_username(username);
    user.set_password(passwordEncoder.encode(password));
    return user_repo.save(user);
  }
}
