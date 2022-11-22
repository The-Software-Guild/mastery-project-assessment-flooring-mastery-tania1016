package org.example.service;

public class StateTaxValidationException extends Exception{

    public StateTaxValidationException(String message) {

        super(message);
    }

    public StateTaxValidationException(String message, Throwable cause) {

        super(message, cause);
    }
}
