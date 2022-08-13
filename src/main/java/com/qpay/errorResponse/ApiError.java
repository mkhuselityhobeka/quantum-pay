package com.qpay.errorResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiError {

    private String message;
    private String debugMessage;
    private HttpStatus httpStatus;
    private LocalDateTime dateTime;
    List<ApiSubError> apiSubErrorList = new ArrayList<>();

     ApiError(){
        this.dateTime = LocalDateTime.now();
    }
     public ApiError(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }

    ApiError(HttpStatus httpStatus,String message, Throwable throwable){
        this.httpStatus = httpStatus;
        this.message = message;
        this.debugMessage = throwable.getLocalizedMessage();
    }
    ApiError(HttpStatus httpStatus, Throwable throwable){
        this.httpStatus = httpStatus;
        this.message = "unexpected error";
        this.debugMessage = throwable.getLocalizedMessage();

    }


}
