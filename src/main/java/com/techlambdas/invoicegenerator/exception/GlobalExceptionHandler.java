package com.techlambdas.invoicegenerator.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<String> handle(InvoiceNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
