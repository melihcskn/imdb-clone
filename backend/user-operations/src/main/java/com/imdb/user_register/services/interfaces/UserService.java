package com.imdb.user_register.services.interfaces;

import com.imdb.user_register.models.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity registerUser(RegisterRequest user) throws Exception;
}
