package io.naav97.balance_checker_api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import io.naav97.balance_checker_api.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByUserFrom(String userFrom);
  List<Transaction> findByUserTo(String userTo);
  List<Transaction> findByUserFromOrUserTo(String userFrom, String userTo);
}
