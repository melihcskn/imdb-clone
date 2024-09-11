package com.MovieSiteProject.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    HttpStatus httpStatus;
    List<String> message;

    public ErrorResponse(HttpStatus httpStatus, String errorMessage){
        this.httpStatus = httpStatus;
        this.message = new ArrayList<>();
        this.message.add(errorMessage);
    }
}
