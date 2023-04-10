package cz.cvut.fel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

  private final Long id;

  private final String number;

  private Long balance;

  @JsonCreator
  public Account(
      @JsonProperty("id") Long id,
      @JsonProperty("number") String number,
      @JsonProperty("balance") Long balance) {
    this.id = id;
    this.number = number;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public Long getBalance() {
    return balance;
  }

  public void setBalance(Long balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "Account{"
        + "id='"
        + id
        + '\''
        + "number='"
        + number
        + '\''
        + ", balance="
        + balance
        + '}';
  }
}
