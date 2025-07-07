package dev.ignas.security.demo.app.exception;

public class WrongDataFormat extends RuntimeException{
    public WrongDataFormat(String message){
        super(message);
    }
}
