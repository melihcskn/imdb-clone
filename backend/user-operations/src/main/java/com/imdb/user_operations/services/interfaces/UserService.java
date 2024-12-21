package com.imdb.user_operations.services.interfaces;

import com.imdb.user_operations.models.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity registerUser(RegisterRequest user) throws Exception;
    public void deleteKeycloakUser(String keycloakUserId);
    public void createKeycloakUser(Map<String,Object> keycloakUserInfo);
}
