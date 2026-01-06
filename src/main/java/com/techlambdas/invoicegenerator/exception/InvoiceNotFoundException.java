package com.techlambdas.invoicegenerator.exception;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String msg) {
        super(msg);
    }
}
