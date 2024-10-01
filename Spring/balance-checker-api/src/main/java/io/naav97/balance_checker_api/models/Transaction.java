package io.naav97.balance_checker_api.models;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transaction {

  private @Id @GeneratedValue Long id;
  private Long userFrom;
  private Long userTo;
  private String type;
  private double amount;
  private LocalDateTime time;

  public Long get_user_from() {
    return this.userFrom;
  }

  public Long get_user_to() {
    return this.userTo;
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

  public void set_user_from(Long p_uid) {
    this.userFrom = p_uid;
  }

  public void set_user_to(Long p_uid) {
    this.userTo = p_uid;
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
