/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alan
 * @param <T>
 */
public interface GenericDAO<T extends Serializable> {
    
    public Integer save(T o);
    
    public void update(T o);
    
    public void delete(T o);
    
    public List<T> findAll(Class<T> o);
    
    public T findById(Integer o);
    
    
    
    
}