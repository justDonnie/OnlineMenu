package com.example.tisoproject.exceptions;

public class BadCredentialException  extends  RuntimeException{
    public BadCredentialException(String s){
        super(s);
    }
}
