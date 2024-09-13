package com.imdb.user_operations.services.interfaces;

import com.imdb.user_operations.models.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity registerUser(RegisterRequest user) throws Exception;
}
