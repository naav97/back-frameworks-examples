package io.naav97.balance_checker_api.models;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {

  private @Id @GeneratedValue Long id;
  private String userFrom;
  private String userTo;
  private double amount;
  private LocalDateTime time;

  public String get_user_from() {
    return this.userFrom;
  }

  public String get_user_to() {
    return this.userTo;
  }

  public double get_amount() {
    return this.amount;
  }

  public LocalDateTime get_time() {
    return this.time;
  }

  public void set_user_from(String p_uid) {
    this.userFrom = p_uid;
  }

  public void set_user_to(String p_uid) {
    this.userTo = p_uid;
  }

  public void set_amount(double p_amo) {
    this.amount = p_amo;
  }

  public void set_time(LocalDateTime p_time) {
    this.time = p_time;
  }
}
