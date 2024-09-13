package com.imdb.user_operations.services.implementations;

import com.imdb.user_operations.services.interfaces.TokenService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class TokenServiceImpl implements TokenService {

    public String token = null;

    @Value("${KEYCLOAK_TOKEN_URL}")
    String requestUrl;

    @Value("${KEYCLOAK_TOKEN_CLIENT_ID}")
    String clientId;

    @Value("${KEYCLOAK_TOKEN_CLIENT_SECRET_KEY}")
    String clientSecret;

    String accessTokenName = "access_token";
    String tokenExpireName = "expires_in";
    String grantTypeName = "client_credentials";

    Calendar tokenExpireDate = Calendar.getInstance();

    @Override
    public Calendar getExpireDate() {
        return tokenExpireDate;
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> setAccessToken() throws Exception {

        //Create and set keycloak login parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", grantTypeName);
        parameters.put("client_id", clientId);
        parameters.put("client_secret", clientSecret);

        //Transform keycloak parameters
        String form = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        //Create new http client
        HttpClient client = HttpClient.newHttpClient();

        //Create and set http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {

            //Send http request
            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //If response has the status code ok then check if the body has access token
            if (response.statusCode() == 200) {
                JSONObject jsonObject = new JSONObject(response.body().toString());
                //If response body has the access token then renew token expire date and return access token
                if (jsonObject.has(accessTokenName)) {
                    tokenExpireDate = Calendar.getInstance();
                    tokenExpireDate.add(Calendar.SECOND, jsonObject.getInt(tokenExpireName));
                    setToken(jsonObject.get(accessTokenName).toString());
                    ResponseEntity<?> responseEntity = new ResponseEntity<>(response.body(), HttpStatus.valueOf(response.statusCode()));
                    return CompletableFuture.completedFuture(responseEntity);
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        //Return token not found message and internal server error
        return CompletableFuture.completedFuture(new ResponseEntity<>("Token not found", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    @PostConstruct
    public void initAccessToken() throws Exception {
        //Set initial access token when program starts
        CompletableFuture.allOf(setAccessToken()).join();
    }
}
