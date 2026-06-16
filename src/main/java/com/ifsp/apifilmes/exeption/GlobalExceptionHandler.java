package com.ifsp.apifilmes.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeDuplicadaException.class)
    public ResponseEntity<?> handleRecursoDuplicado(EntidadeDuplicadaException ex) {
        Map<String, Object> body =  new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", ex.getErro());
        body.put("message", ex.getMensagem());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadeNaoEncontrdaException.class)
    public ResponseEntity<?> handleRecusoNaoEncontrado(EntidadeNaoEncontrdaException ex) {
        Map<String, Object> body =  new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", ex.getErro());
        body.put("message", ex.getMensagem());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
