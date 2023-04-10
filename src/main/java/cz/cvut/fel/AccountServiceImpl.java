package cz.cvut.fel;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AccountServiceImpl implements AccountService {

  private final RestClient client;
  private final ObjectMapper objectMapper;

  public AccountServiceImpl(RestClient client, ObjectMapper objectMapper) {
    this.client = client;
    this.objectMapper = objectMapper;
  }

  @Override
  public Account getAccount(Long id) {
    Account account = null;
    String stringResponse =
        client.get(
            String.format("https://643433e81c5ed06c9591cdfa.mockapi.io/api/v1/account/%d", id));

    if (stringResponse != null) {
      try {
        account = objectMapper.readValue(stringResponse, Account.class);
      } catch (IOException e) {
        System.err.println("Error happened during parsing the response");
      }
    }
    return account;
  }
}
