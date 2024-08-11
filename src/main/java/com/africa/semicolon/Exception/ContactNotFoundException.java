package com.africa.semicolon.Exception;

public class ContactNotFoundException extends  RuntimeException{
    public ContactNotFoundException(String message){
        super(message);
    }
}
