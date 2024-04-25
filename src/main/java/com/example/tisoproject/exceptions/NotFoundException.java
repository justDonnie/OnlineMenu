package com.example.tisoproject.exceptions;

public class NotFoundException extends  RuntimeException{
    public NotFoundException(String s){
        super(s);
    }
}
