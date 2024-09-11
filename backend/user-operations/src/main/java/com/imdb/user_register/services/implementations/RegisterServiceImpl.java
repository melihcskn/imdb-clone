package com.imdb.user_register.services.implementations;

import com.imdb.user_register.models.entity.User;
import com.imdb.user_register.services.interfaces.RegisterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Value("${KEYCLOAK_REGISTER_URL}")
    private String registerUrl;

    @Value("${KEYCLOAK_LOGIN_URL}")
    private String loginUrl;

    @Value("${KEYCLOAK_LOGIN_CLIENT_ID}")
    private String loginClientId;

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> register(User user, String token) throws IOException {
        //Set register url
        URL url = new URL(registerUrl);

        //Create http connection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //Set keycloak credentials
        ArrayList<JSONObject> credentials = new ArrayList<>();
        credentials.add(new JSONObject().put("temporary", false).put("type", "password").put("value", user.getPassword()));

        //Create a JSON object that contains keycloak registration properties and set them
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getUsername());
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("enabled", true);
        jsonObject.put("credentials", credentials);

        //Set request properties
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + token);
        con.setDoOutput(true);

        try {
            //Send the request
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

            //Since this method is async, we use CompletableFuture variable as result
            return CompletableFuture.completedFuture(new ResponseEntity<>(con.getResponseMessage(), HttpStatus.valueOf(con.getResponseCode())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return CompletableFuture.completedFuture(new ResponseEntity<>("User registration failed", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> logIn(User user){

        //Create and set keycloak login parameters
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "password");
        parameters.put("client_id", loginClientId);
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());

        //Transform keycloak parameters
        String form = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        //Create http client
        HttpClient client = HttpClient.newHttpClient();

        //Create and set http request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        try {
            //Send the http request
            HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Since this method is async, we use CompletableFuture variable as result
            return CompletableFuture.completedFuture(new ResponseEntity<>(response.body(),HttpStatus.valueOf(response.statusCode())));

        } catch (Exception e) {
            return CompletableFuture.completedFuture(new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

}
