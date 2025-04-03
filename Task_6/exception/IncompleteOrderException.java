package com.techshop.exception;

public class IncompleteOrderException extends RuntimeException {
    public IncompleteOrderException(String message) {
        super(message);
    }
}