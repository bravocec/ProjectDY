/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import turing.solutions.dy.persistence.dao.usuarios.UsuariosDAO;
import turing.solutions.dy.persistence.model.Usuarios;

/**
 *
 * @author Alan
 */
@Service
public class CommonProvider implements CommonService{
    
    private Logger log = Logger.getLogger(CommonProvider.class);
    
    @Autowired
    private UsuariosDAO<Usuarios> usuariosDAO;

    @Override
    public Usuarios currentUser() {
        Usuarios currentUser = null;
        String userName = null;
        try{
            log.info("Obteninendo el username del usuario");
            userName = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info("User name obtenido del usuario: "+userName);
            currentUser = this.usuariosDAO.findByCorreo(userName);
        }catch(Exception e){
            log.error("Error al obtener el usuario de base de datos con el context: "+userName,e);
        }
        return currentUser;
    }
    
}
