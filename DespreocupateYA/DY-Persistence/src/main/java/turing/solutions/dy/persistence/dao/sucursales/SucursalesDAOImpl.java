/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.sucursales;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import turing.solutions.dy.persistence.dao.GenericDAOImpl;

/**
 *
 * @author Alan
 * @param <T>
 */
@Repository
public class SucursalesDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements SucursalesDAO<T>{

    @Override
    public T findById(Integer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
