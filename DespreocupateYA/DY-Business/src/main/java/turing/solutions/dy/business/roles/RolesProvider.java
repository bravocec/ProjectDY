/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.roles;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import turing.solutions.dy.persistence.dao.roles.RolesDAO;
import turing.solutions.dy.persistence.model.Roles;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 */
@Service
public class RolesProvider implements RolesService{
    
    @Autowired
    private RolesDAO<Roles> rolesDAO;
    
    @Override
    public List<Roles> findByDescRol(String desRol){
        List<Roles> roles = null;
        try{
            roles = this.rolesDAO.findByDescRol(desRol);
        }catch(Exception e){
            throw new DYRuntimeException("Error al obtener el rol", e);
        }
        return roles;
    }
    
}
