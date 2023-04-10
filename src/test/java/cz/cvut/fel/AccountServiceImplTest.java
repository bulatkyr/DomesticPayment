package cz.cvut.fel;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class AccountServiceImplTest {

  @Test
  public void getAccount_test_positive() {
    OkHttpClient client = new OkHttpClient();
    RestClient restClient = new RestClient(client);
    ObjectMapper mapper = new ObjectMapper();

    AccountService accountService = new AccountServiceImpl(restClient, mapper);

    Account account = accountService.getAccount(1L);
    assertNotNull(account);
    assertEquals(1, account.getId());
    assertEquals("29160954", account.getNumber());
    assertEquals(4, account.getBalance());
  }

  @Test
  public void getAccount_test_positive_mocked() {
    ObjectMapper mapper = new ObjectMapper();
    RestClient mockRestClient = Mockito.mock(RestClient.class);

    Mockito.when(mockRestClient.get(anyString()))
        .thenReturn("{\"number\": \"123\", \"id\":1, \"balance\":4}");

    AccountService accountService = new AccountServiceImpl(mockRestClient, mapper);
    Account account = accountService.getAccount(1L);

    assertEquals(1, account.getId());
    assertEquals("123", account.getNumber());
    assertEquals(4, account.getBalance());
  }

  @Test
  public void getAccount_test_positive_mocked_verification() {
    ObjectMapper mapper = new ObjectMapper();
    RestClient mockRestClient = Mockito.mock(RestClient.class);

    Mockito.when(mockRestClient.get(anyString()))
        .thenReturn("{\"number\": \"123\", \"id\":1, \"balance\":4}");

    AccountService accountService = new AccountServiceImpl(mockRestClient, mapper);
    Account account = accountService.getAccount(1L);

    Mockito.verify(mockRestClient)
        .get("https://643433e81c5ed06c9591cdfa.mockapi.io/api/v1/account/1");
  }

  @Test
  public void getAccount_test_negative_mocked() {
    ObjectMapper mapper = new ObjectMapper();
    RestClient mockRestClient = Mockito.mock(RestClient.class);

    Mockito.when(mockRestClient.get(anyString()))
        .thenReturn(null);

    AccountService accountService = new AccountServiceImpl(mockRestClient, mapper);
    Account account = accountService.getAccount(1L);

    assertNull(account);
  }


}
