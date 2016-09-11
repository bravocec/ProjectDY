/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.util;

import java.util.Properties;

/**
 *
 * @author Alan
 */
public class CommonProperties {
    
    private static final Properties properties = new Properties();
    private static final String RUTA_PROPERTIES = "/dy.properties";
    
    static{
        try{
            properties.load(CommonProperties.class.getResourceAsStream(RUTA_PROPERTIES));
        }catch(Throwable e){
            e.printStackTrace();
        }
    }
    
    public static String getResource(String name){
        return properties.getProperty(name);
    }
    
}
