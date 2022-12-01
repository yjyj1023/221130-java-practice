package com.lambda.practice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalException extends RuntimeException{
    ErrorCode errorCode;
    String message;
}
