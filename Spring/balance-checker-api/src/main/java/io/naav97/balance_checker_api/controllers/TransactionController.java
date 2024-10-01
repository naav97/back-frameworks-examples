package io.naav97.balance_checker_api.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.naav97.balance_checker_api.repositories.TransactionRepository;
import io.naav97.balance_checker_api.repositories.UserRepository;

@RestController
@RequestMapping("/api/trans")
public class TransactionController {

  @Autowired
  private UserRepository user_repo;
  @Autowired
  private TransactionRepository trans_repo;

  @PostMapping("/create")
  public ResponseEntity<String> createTrans(@RequestParam Long user_from, @RequestParam Long user_to, @RequestParam String type, @RequestParam double amount, @RequestParam LocalDateTime time) {
    //ResponseEntity;
  }
}
