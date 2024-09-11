package com.imdb.user_register.controller;

import com.imdb.user_register.messaging.RabbitMQProducer;
import com.imdb.user_register.models.request.RegisterRequest;
import com.imdb.user_register.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public UserController(UserService userService, RabbitMQProducer rabbitMQProducer) {
        this.userService = userService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest user) {
        try {
            return userService.registerUser(user);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/test")
    public ResponseEntity<?> test() {
        rabbitMQProducer.sendMessage("User controller class");
        return ResponseEntity.ok("Succeeded");
    }
}
