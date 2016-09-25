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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RestController
@RequestMapping("/micuenta")
public class DireccionesController {
    
    private Logger log = Logger.getLogger(DireccionesController.class);
    
    private static final String STATUS = "status";
    
    @Autowired
    private DireccionesService direccionesService;
    
    
    @RequestMapping(value = "/obtenerDirecciones",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getDireccionesActuales(){
        Map<String,Object> map = new HashMap<>();
        log.info("Obteniendo las direcciones");
        try{
            List<Domicilios> domicilios = this.direccionesService.getDomicilios();
            map.put("direcciones", domicilios != null ? domicilios : 0);
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("Hubo un error al momento de obtener las direcciones",e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return map;
    }
    
    
    @RequestMapping(value="/guardaDomicilio",method = RequestMethod.POST,produces = "application/json")
    public Map<String,Object> guardaDomicilio(@RequestBody Domicilios domicilio){
        Map<String,Object> map = new HashMap<>();
        log.info("Guardando las direcciones");
        log.info(domicilio.toString());
        try{
            this.direccionesService.guardaDireccion(domicilio);
            map.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("Error al guardar la direccion",e);
            map.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return map;
    }
    
    @RequestMapping(value="/eliminarDomicilio",method = RequestMethod.POST,produces = "application/json")
    public Map<String,Object> eliminarDomicilio(@RequestBody Domicilios domicilio){
        Map<String,Object> response = new HashMap<>();
        log.info("Borrando el domicilio "+domicilio.getIdDomicilio()); 
        try{
            this.direccionesService.borraDomicilio(domicilio);
        }catch(Exception e){
            log.error("Error al eliminar",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return response;
    }
    
    
    @RequestMapping(value="/domicilio/{idDireccion}",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getDireccionById(@PathVariable Integer idDireccion){
        Map<String,Object> response = new HashMap<>();
        try{
            Domicilios domicilio = this.direccionesService.getDomicilioById(idDireccion);
            response.put("domicilio", domicilio);
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("Error al eliminar el domicilio",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return response;
    }
    
    @RequestMapping(value="/actualizarDomicilio",method = RequestMethod.POST,produces = "application/json")
    public Map<String,Object> actualizaDomicilio(@RequestBody Domicilios domicilio){
        Map<String,Object> response = new HashMap<>();
        try{
            this.direccionesService.actualizaDomicilio(domicilio);
            response.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
        }catch(Exception e){
            log.error("No se pudo actualizar el domicilio ",e);
            response.put(STATUS, DYGeneralCodeMessages.ERROR_GENERAL.getCodigo());
        }
        return response;
    }
    
}
