package com.imdb.user_operations.services.implementations;

import com.imdb.user_operations.exceptions.AlreadyExistException;
import com.imdb.user_operations.models.Mapper;
import com.imdb.user_operations.models.entity.User;
import com.imdb.user_operations.models.request.RegisterRequest;
import com.imdb.user_operations.repositories.UserRepository;
import com.imdb.user_operations.security.token.TokenUtil;
import com.imdb.user_operations.services.interfaces.RegisterService;
import com.imdb.user_operations.services.interfaces.TokenService;
import com.imdb.user_operations.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;


@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    TokenService tokenService;
    RegisterService registerService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenService tokenService, RegisterService registerService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.registerService = registerService;
    }

    //Register new user, if succeeded then return access token
    @Override
    public ResponseEntity<?> registerUser(RegisterRequest requestedUser) throws Exception {

        //Get user if exist
        User userByEmail = userRepository.findUserByEmail(requestedUser.getEmail());
        User userByUsername = userRepository.findUserByUsername(requestedUser.getUsername());

        //If user exist throw an exception
        if (userByEmail != null || userByUsername != null) {
            throw new AlreadyExistException("User already exists");
        }

        return setUser(requestedUser);

    }

    private ResponseEntity<?> setUser(RegisterRequest requestedUser) throws Exception {
        //Our registration relies on access token, therefore if token
        //is null or expired try to renew token
        if (tokenService.getToken() == null || tokenService.getExpireDate().compareTo(Calendar.getInstance()) <= 0) {

            CompletableFuture<ResponseEntity<?>> responseCode = tokenService.setAccessToken();

            CompletableFuture.allOf(responseCode);

            //If fetch is succeeded that means we renewed our access token
            //So we call this method and this time it will execute to else phase
            if (responseCode.get().getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<>("Failed to fetch access token", responseCode.get().getStatusCode());
            } else {
                setUser(requestedUser);
            }
        } else {

            Mapper mapper = new Mapper();

            //Convert RegisterRequest type to User
            User userToRegister = mapper.convertRegisterRequestToUser(requestedUser);

            //First register our new user to Keycloak service
            CompletableFuture<ResponseEntity<?>> registerResponse = registerService.register(userToRegister, tokenService.getToken());

            //Wait until register function is done
            CompletableFuture.allOf(registerResponse).join();

            //If registration is succeeded, get user login token and save user to database
            if (registerResponse.get().getStatusCode() == HttpStatus.CREATED) {

                //Get access token for new registered user
                CompletableFuture<ResponseEntity<?>> logInResponse = registerService.logIn(userToRegister);

                //Wait until login is succeeded
                CompletableFuture.allOf(logInResponse).join();

                //If there is a valid login response then set user's keycloakid and save it to database
                //Also return access token
                if (logInResponse.get().getStatusCode() == HttpStatus.OK && logInResponse.get().getBody().toString() != null) {
                    TokenUtil tokenUtil = new TokenUtil();
                    String accessToken = tokenUtil.getAccessToken(logInResponse.get().getBody().toString());
                    String keycloakId = tokenUtil.getUserKeycloakId(accessToken);
                    userToRegister.setKeycloakId(keycloakId);
                    userRepository.save(userToRegister);
                    System.out.println(accessToken);
                    return new ResponseEntity<>(accessToken, HttpStatus.CREATED);
                } else {
                    //If login response was failed then just save the user to database
                    userRepository.save(userToRegister);
                    return new ResponseEntity<>("User created but could not retrieved access token", HttpStatus.CREATED);
                }
            } else {
                return new ResponseEntity<>("Couldn't registered the user", registerResponse.get().getStatusCode());
            }

        }
        return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
