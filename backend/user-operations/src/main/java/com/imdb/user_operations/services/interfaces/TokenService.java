package com.imdb.user_operations.services.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public interface TokenService {
    CompletableFuture<ResponseEntity<?>> setAccessToken() throws Exception;
    void initAccessToken() throws Exception;
    String getToken();
    Calendar getExpireDate();
}
