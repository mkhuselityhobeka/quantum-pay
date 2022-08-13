package com.qpay.exceptions;

import com.qpay.errorResponse.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object>handleResourceNotFound(ResourceNotFoundException resorceNotFound){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(resorceNotFound.getMessage());
        apiError.setDebugMessage(resorceNotFound.getLocalizedMessage());
        apiError.setDateTime(LocalDateTime.now());
        return buildResponseEntity(apiError);

    }

    @Override
    public ResponseEntity<Object>handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                              HttpHeaders httpHeaders,
                                                              HttpStatus httpStatus, WebRequest webRequest){

        ApiError apiError = new ApiError(httpStatus);
        apiError.setMessage("Validation Erroe");

        return null;
    }

    private ResponseEntity<Object>buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

}
