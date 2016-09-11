/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.business.roles;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import turing.solutions.dy.persistence.dao.roles.RolesDAO;
import turing.solutions.dy.persistence.model.Roles;

/**
 *
 * @author Alan
 */
public interface RolesService {
    
    List<Roles> findByDescRol(String desRol);
    
}
