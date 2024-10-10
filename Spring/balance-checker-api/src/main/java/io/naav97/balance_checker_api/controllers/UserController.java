package io.naav97.balance_checker_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.naav97.balance_checker_api.services.AuthService;
import io.naav97.balance_checker_api.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  private AuthService auth_service;
  @Autowired
  private UserService user_service;

  @PostMapping("/create")
  public ResponseEntity<String> createUser(@RequestParam String username, @RequestParam String password) {
    try {
      auth_service.createAccount(username, password);
      return ResponseEntity.ok("Account created");
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
    if (auth_service.login(username, password)) {
      return ResponseEntity.ok("Login successful");
    }
    else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }
  }

  @PutMapping("/{userId}/addb")
  public ResponseEntity<String> addBalance(@PathVariable String userId, @RequestParam double amount) {
    try {
      double newb = user_service.add_balance(userId, amount);
      return ResponseEntity.ok("New balance: " + newb);
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
