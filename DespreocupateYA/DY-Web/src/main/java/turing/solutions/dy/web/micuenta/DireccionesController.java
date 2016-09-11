/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.micuenta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import turing.solutions.dy.business.micuenta.DireccionesService;
import turing.solutions.dy.persistence.model.Domicilios;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;

/**
 *
 * @author Alan
 */
@RestController//Siempre va, por que es el que hace controlador Web la clase, y con ese te puedes comunicar.
@RequestMapping("/micuenta")//El contexto general de micuenta
public class DireccionesController {
    
    private Logger log = Logger.getLogger(DireccionesController.class);
    
    private static final String STATUS = "status";
    
    @Autowired
    private DireccionesService direccionesService;
    
    
    @RequestMapping(value = "/obtenerDirecciones",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getDireccionesActuales(@RequestParam(value = "idUsuario") String idUsuario){
        Map<String,Object> map = new HashMap<>();
        log.info("--Obteniendo las direcciones del usuario "+idUsuario);
        try{
            List<Domicilios> domicilios = this.direccionesService.getDomicilios(idUsuario);
            log.info("Tamaño de la respuesta "+domicilios.size());
            map.put("direcciones", domicilios != null ? domicilios : 0);
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("Hubo un error al momento de obtener las direcciones",e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return map;
    }
    
}
