package io.naav97.balance_checker_api.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.naav97.balance_checker_api.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  Optional<Transaction> findByUserId(Long userId);
}
