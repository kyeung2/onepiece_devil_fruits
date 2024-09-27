package com.nimbus.onepiece.devilfruits.presentation;

import com.nimbus.onepiece.devilfruits.interfaces.dto.errors.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //TODO need domain specific Exceptions that can be transformed into ErrorDto objects
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(RuntimeException e) {
        return ResponseEntity.status(500)
                .body(ErrorDto.builder()
                        .code("DFE001")
                        .message(e.getMessage())
                        .build());
    }
}
