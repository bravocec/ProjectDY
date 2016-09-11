/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.usuarios;

import java.io.Serializable;
import org.hibernate.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.GenericDAOImpl;
import turing.solutions.dy.util.exceptions.DYRuntimeException;

/**
 *
 * @author Alan
 * @param <T>
 */
@Repository
public class UsuariosDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements UsuariosDAO<T> {
    
    
    @Override
    @Transactional(readOnly = true)
    public T findById(Integer o) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByIdUsuario");
        query.setParameter("idUsuario", o);
        
        Object result = query.uniqueResult();
        
        return result != null ? (T) result : null;
    }

    @Override
    @Transactional(readOnly = true)
    public T findByCorreo(String correo) {
        final String named = ENTITY_NAME + ".findByEmail";
        System.out.println("correo "+correo);
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(named);
        query.setParameter("email", correo);
        Object result = query.uniqueResult();
        return result != null ? (T) result  : null ;
    }
    
    @Override
    @Transactional
    public T findByRFC(String rfc){
        final String namedQuery = ENTITY_NAME + ".findByRfc";
        
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(namedQuery);
        query.setParameter("rfc", rfc);
        Object result = null;
        try{
            result = query.uniqueResult();
        }catch(Exception e){
            throw new DYRuntimeException("Error al obtener buscar por rfc", e);
        }
        return result != null ? (T) result : null;
    }
    
}
