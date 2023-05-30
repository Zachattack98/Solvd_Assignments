package com.solvd.lab2.exception;

public class PriceInvalidException extends RuntimeException {
    public PriceInvalidException (String message) {
        super(message);
    }
}