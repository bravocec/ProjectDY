/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.roles;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.GenericDAOImpl;

/**
 *
 * @author Alan
 * @param <T>
 */
@Repository
public class RolesDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements RolesDAO<T>{

    @Override
    public T findById(Integer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByDescRol(String descRol) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByDescRol");
        query.setParameter("descRol", descRol);
        List result = query.list();
        return result != null ? (List<T>) result : null;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findByDescRol(String descRol,String descRol2) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByRolesVarios");
        query.setParameter("desc1", descRol);
        query.setParameter("desc2", descRol2);
        List result = query.list();
        return result != null ? (List<T>) result : null;
    }
    
}
