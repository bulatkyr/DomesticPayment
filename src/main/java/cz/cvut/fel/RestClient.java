package cz.cvut.fel;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RestClient {

  private final OkHttpClient client;

  public RestClient(OkHttpClient client) {
    this.client = client;
  }

  public String get(String url) {
    String stringResponse = null;
    Request request = new Request.Builder().url(url).build();

    try (Response response = client.newCall(request).execute()) {
      if (response.body() != null) {
        stringResponse = response.body().string();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return stringResponse;
  }
}
