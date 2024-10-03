package io.naav97.balance_checker_api.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.naav97.balance_checker_api.repositories.UserRepository;
import io.naav97.balance_checker_api.services.TransService;

@RestController
@RequestMapping("/api/trans")
public class TransactionController {

  @Autowired
  private UserRepository user_repo;
  @Autowired
  private TransService trans_service;

  @PostMapping("/create")
  public ResponseEntity<String> createTrans(@RequestParam String user_from, @RequestParam String user_to, @RequestParam double amount, @RequestParam LocalDateTime time) {
    try {
      trans_service.create(user_from, user_to, amount, time);
      return ResponseEntity.ok("Transaction created");
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
