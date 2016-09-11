/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.web.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import turing.solutions.dy.business.registro.usuarios.UsuariosService;
import turing.solutions.dy.persistence.model.Usuarios;
import turing.solutions.dy.util.enums.DYGeneralCodeMessages;
import turing.solutions.dy.util.enums.DYGeneralEnums;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Controller
@RequestMapping("/log")
public class LoginController {

    private static final String INDEX = "index";
    private static final String STATUS = "status";

    @Autowired
    private UsuariosService usuariosService;

    
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public @ResponseBody List<Usuarios> getAllUsers() {
        List<Usuarios> list = null;
        try {
            list = this.usuariosService.getAllUsers();

        } catch (Exception e) {
            System.out.println("Error al obtener todos los  usuarios");
            e.printStackTrace();
            list = new ArrayList<>();
        }
        return list;
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<String, Object> getUserInfo(@RequestParam(value = "user")String user) {
        Map<String, Object> userInfo = new HashMap<String, Object>();
        Usuarios usuario  = null;
        
        if(user == null || user.trim().isEmpty()){
            userInfo.put("status", "-1");
            return userInfo;
        }
        try{
            usuario = usuariosService.findByCorreo(user);
            if(usuario != null ){
                userInfo.put(DYGeneralEnums.ID_USER.getValue(), usuario.getIdUsuario());
                userInfo.put(DYGeneralEnums.NOMBRES.getValue(), usuario.getNombres());
                userInfo.put(DYGeneralEnums.EMAIL.getValue(), usuario.getEmail());
                userInfo.put(DYGeneralEnums.ROL.getValue(), usuario.getRolesList());
                userInfo.put(STATUS, DYGeneralCodeMessages.SUCCESS.getCodigo());
            }else{
                //TODO: Pasar esto a un catalogo enum de errores
                userInfo.put("status", "0");
            }
        }catch(DYRuntimeException  e){
            userInfo.put("status", "500");
        }
        
        return userInfo;
    }
    
    @RequestMapping(value="/valida",method = RequestMethod.GET,produces = "application/json")
    public @ResponseBody Map<String,Object> validaToken(){
        Map map = new HashMap();
        map.put("status", "200");
        System.out.println("ok");
        return map;
    }

}
