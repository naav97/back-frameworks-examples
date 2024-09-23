package io.naav97.balance_checker_api.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.naav97.balance_checker_api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
