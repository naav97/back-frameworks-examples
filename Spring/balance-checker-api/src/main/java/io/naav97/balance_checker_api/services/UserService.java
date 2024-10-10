package io.naav97.balance_checker_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.naav97.balance_checker_api.repositories.UserRepository;
import io.naav97.balance_checker_api.models.User;

@Service
public class UserService {

  private final UserRepository user_repo;

  @Autowired
  public UserService(UserRepository ur) {
    this.user_repo = ur;
  }

  public double add_balance(String user, double amount) {
    Optional<User> user_inst = user_repo.findByUsername(user);
    if (!user_inst.isPresent()) {
      throw new RuntimeException("User not found");
    }
    User u = user_inst.get();
    u.set_balanace(u.get_balance() + amount);
    user_repo.save(u);
    return u.get_balance();
  }
}
