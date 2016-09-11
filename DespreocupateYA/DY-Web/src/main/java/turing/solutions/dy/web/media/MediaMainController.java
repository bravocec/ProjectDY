/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.media;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import turing.solutions.dy.business.media.MediaService;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;

/**
 *
 * @author Alan
 */
@RestController
@RequestMapping("/media")
public class MediaMainController {
    
    private static final String STATUS = "status";
    
    private final Logger log = Logger.getLogger(MediaMainController.class);
    
     @Autowired
     private MediaService mediaService;
    
    @RequestMapping(value="/secciones",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getSeccionesIndex(){
        Map<String,Object> map = new HashMap<>();
        
        try{
            Map<String,Object> secciones = this.mediaService.getImagenesIndex();
            map.put("secciones", (secciones == null || secciones.isEmpty()) ? 
                    "0" : secciones);
        }catch(Exception e){
            log.error("Hubo un excecpcion al momento de obtener las imagenes del index", e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return map;
    }
}
