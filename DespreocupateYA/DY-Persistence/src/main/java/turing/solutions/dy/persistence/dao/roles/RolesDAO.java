/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.roles;

import java.io.Serializable;
import java.util.List;
import turing.solutions.dy.persistence.dao.GenericDAO;

/**
 *
 * @author Alan
 * @param <T>
 */
public interface RolesDAO<T extends Serializable> extends GenericDAO<T> {
    
    public static final String ENTITY_NAME = "Roles";
    
    List<T> findByDescRol(String descRol);
    
    List<T> findByDescRol(String descRol,String descRol2);
    
}
