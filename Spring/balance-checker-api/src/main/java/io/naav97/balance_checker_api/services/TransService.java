package io.naav97.balance_checker_api.services;

import org.springframework.stereotype.Service;

import io.naav97.balance_checker_api.repositories.TransactionRepository;
import io.naav97.balance_checker_api.repositories.UserRepository;

@Service
public class TransService {

  private final UserRepository user_repo;
  private final TransactionRepository trans_repo;
}
