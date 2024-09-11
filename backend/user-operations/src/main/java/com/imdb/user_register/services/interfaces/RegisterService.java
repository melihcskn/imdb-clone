package com.imdb.user_register.services.interfaces;

import com.imdb.user_register.models.entity.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface RegisterService {
    CompletableFuture<ResponseEntity<?>> register(User user, String token) throws IOException;
    CompletableFuture<ResponseEntity<?>> logIn(User user) throws IOException;
}
