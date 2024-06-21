package com.graduationProject.medicory.exception;

public class VoiceRecordNotFoundException extends RuntimeException{
    public VoiceRecordNotFoundException(String message){
        super(message);
    }
    public VoiceRecordNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
