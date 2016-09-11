/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.categorias;

import java.io.Serializable;
import turing.solutions.dy.persistence.dao.GenericDAO;

/**
 *
 * @author Alan
 */
public interface CategoriasServiciosDAO<T extends Serializable> extends GenericDAO<T>{
    
    public static final String ENTITY_NAME = "CategoriasServicios";
    
    T findCategoriaByDescripcion(String desc);
    
}
