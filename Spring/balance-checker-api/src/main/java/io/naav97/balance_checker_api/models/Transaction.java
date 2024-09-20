package io.naav97.balance_checker_api;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {

  private @Id @GeneratedValue Long id;
  private Long user_id;
  private String type;
  private double amount;
  private LocalDateTime time;

  public Long get_user_id() {
    return this.user_id;
  }

  public String get_type() {
    return this.type;
  }

  public double get_amount() {
    return this.amount;
  }

  public LocalDateTime get_time() {
    return this.time;
  }

  public void set_user_id(Long p_uid) {
    this.user_id = p_uid;
  }

  public void set_type(String p_type) {
    this.type = p_type;
  }

  public void set_amount(double p_amo) {
    this.amount = p_amo;
  }

  public void set_time(LocalDateTime p_time) {
    this.time = p_time;
  }
}
