package com.imdb.user_operations.controller;

import com.imdb.user_operations.models.request.RegisterRequest;
import com.imdb.user_operations.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest user) {
        try {
            return userService.registerUser(user);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
