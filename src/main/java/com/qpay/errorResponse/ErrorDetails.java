package com.qpay.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorDetails extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ErrorDetails(String object, String message){
        this.object = object;
        this.message = message;

    }
}
