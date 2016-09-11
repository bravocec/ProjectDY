/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.micuenta;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import turing.solutions.dy.business.micuenta.DatosPersonalesService;
import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
@RestController
@RequestMapping("/micuenta")
public class DatosPersonalesController {
    
    private static final Logger loger = Logger.getLogger(DatosPersonalesController.class);
    
    @Autowired
    private DatosPersonalesService service;
    
    @RequestMapping(value="/datosPersonales",method = RequestMethod.GET,produces = "application/json")
    public Map<String,Object> getDatosPersonales(@RequestParam(value = "idUsuario")String idUsuario){
        
        Map<String,Object> map = new HashMap<String, Object>();
        
        loger.info("Llegué al controller DatosPersonalesController, metodo getDatosPersonales");
        loger.info("id Usuario "+idUsuario);
        try{
            Usuarios usuario = this.service.getDatosPersonales(Integer.parseInt(idUsuario));
            map.put("nombres", usuario.getNombres());
            map.put("apellidos", usuario.getApellidos());
            map.put("email", usuario.getEmail());
            map.put("telefono", "5578995263");
        }catch(Exception e){
            loger.error("Hubo un error",e);
        }
        return map;
    }
    
    @RequestMapping(value="/guardaDatos",method = RequestMethod.POST,produces = "application/json")
    public Map<String,Object> guardaCambioDatosPersonales(
            @RequestParam(value = "idUsuario") String idUsuario,
            @RequestParam(value = "nombres") String nombres,
            @RequestParam(value = "apellidos") String apellidos,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "telefono") String telefono
            ){
        Map<String, Object> map = new HashMap<>();
        try{
            this.service.actualizaDatos(idUsuario, nombres, apellidos, email, telefono);
            map.put("estatusactualizacion","Exitoso");
        }catch(Exception e){
            loger.error("e: " + e.getMessage());
            map.put("estatusactualizacion","No exitoso");
        }
        return map;
    }    
}
