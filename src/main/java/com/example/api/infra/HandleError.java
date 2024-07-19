package com.example.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandleError {

    public record MsgErrors(String field, String msg){
        public MsgErrors(FieldError err){
            this(err.getField(), err.getDefaultMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> argumentsInvalid(MethodArgumentNotValidException ex){
        List<MsgErrors> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(MsgErrors::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    public record ErrorResponse(String message) {}

}