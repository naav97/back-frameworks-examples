package io.naav97.balance_checker_api.controllers;

import java.time.LocalDateTime;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.naav97.balance_checker_api.services.TransService;
import io.naav97.balance_checker_api.models.Transaction;

@RestController
@RequestMapping("/api/trans")
public class TransactionController {

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

  @GetMapping("/get")
  public ResponseEntity<List<Transaction>> getTrans(@RequestParam String user) {
    return ResponseEntity.ok(trans_service.get_all(user));
  }
}
