package com.alkemy.disney.exeption;

public class ParameterNotFound extends  RuntimeException {
    public ParameterNotFound(String error) {
        super(error); }
}
