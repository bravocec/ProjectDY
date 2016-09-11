/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.util;

/**
 *
 * @author Alan
 */
public class DYGeneralUtils {
    
    public static boolean validaEntradas(String... elements){
        if(elements == null || elements.length == 0){
            return false;
        }
        for(String str: elements){
            if(str == null || str.trim().isEmpty() || str.trim().equals("")){
                return false;
            }
        }
        return true;
    }
    
}
