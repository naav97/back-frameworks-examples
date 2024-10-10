package io.naav97.balance_checker_api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.naav97.balance_checker_api.models.Transaction;
import io.naav97.balance_checker_api.models.User;
import io.naav97.balance_checker_api.repositories.TransactionRepository;
import io.naav97.balance_checker_api.repositories.UserRepository;

@Service
public class TransService {

  private final UserRepository user_repo;
  private final TransactionRepository trans_repo;

  @Autowired
  public TransService(UserRepository ur, TransactionRepository tr) {
    this.user_repo = ur;
    this.trans_repo = tr;
  }

  public Transaction create(String user_from, String user_to, double amount, LocalDateTime time) {
    Optional<User> user_from_inst = user_repo.findByUsername(user_from);
    Optional<User> user_to_inst = user_repo.findByUsername(user_to);
    if (!user_from_inst.isPresent()) {
      throw new RuntimeException("User not found");
    }
    if (!user_to_inst.isPresent()) {
      throw new RuntimeException("User not found");
    }
    if (user_from_inst.get().get_balance() < amount) {
      throw new RuntimeException("Insufficient balance");
    }
    user_from_inst.get().set_balanace(user_from_inst.get().get_balance() - amount);
    user_to_inst.get().set_balanace(user_to_inst.get().get_balance() + amount);
    Transaction trans = new Transaction();
    trans.set_user_from(user_from);
    trans.set_user_to(user_to);
    trans.set_amount(amount);
    trans.set_time(time);
    return trans_repo.save(trans);
  }

  public List<Transaction> get_all(String user) {
    return trans_repo.findByUserFromOrUserTo(user, user);
  }
}
