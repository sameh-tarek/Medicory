package com.graduationProject.medicory.exception;

public class ResutExistsException extends RuntimeException{
    public ResutExistsException(String message){
        super(message);
    }
    public ResutExistsException(String message , Throwable cause){
        super(message,cause);
    }
}
