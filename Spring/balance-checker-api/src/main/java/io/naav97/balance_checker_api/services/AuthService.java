package io.naav97.balance_checker_api.services;
import org.springframework.stereotype.Service;

import io.naav97.balance_checker_api.repositories.UserRepository;

@Service
public class AuthService {

  private final UserRepository user_repo;
}
