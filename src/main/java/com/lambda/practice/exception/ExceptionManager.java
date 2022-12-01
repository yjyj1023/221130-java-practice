package com.lambda.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e){
        //RuntimeException이 나면 Controller 대신 여기서 리턴을 해준다.
        //RuntimeException이 났을 때 INTERNAL_SERVER_ERROR를 리턴하고 ResponseBody에 e.getMessage()를 추가해서 보낸다.
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(HospitalException.class)
    public ResponseEntity<?> HospitalExceptionHandler(HospitalException hospitalException){
        //
        return ResponseEntity.status(hospitalException.getErrorCode().getHttpStatus())
                .body(hospitalException.getMessage());
    }


}
