/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.util.exceptions;

/**
 *
 * @author Alan
 */
public class DYRuntimeException extends RuntimeException{
    
    public DYRuntimeException(String message){
        super(message);
    }
    
    public DYRuntimeException(String message, Throwable cause){
        super(message, cause);
    }
    
    public DYRuntimeException(Throwable cause){
        super(cause);
    }
    
}
