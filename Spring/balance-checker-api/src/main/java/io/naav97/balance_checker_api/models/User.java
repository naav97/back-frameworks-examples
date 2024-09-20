package io.naav97.balance_checker_api;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

  private @Id @GeneratedValue Long id;
  private String username;
  private String password;
  private double balance;

  public String get_username() {
    return this.username;
  }

  public String get_password() {
    return this.password;
  }

  public double get_balance() {
    return this.balance;
  }

  public void set_username(String p_uname) {
    this.username = p_uname;
  }

  public void set_password(String p_pass) {
    this.password = p_pass;
  }

  public void set_balanace(double p_bal) {
    this.balance = p_bal;
  }
}
