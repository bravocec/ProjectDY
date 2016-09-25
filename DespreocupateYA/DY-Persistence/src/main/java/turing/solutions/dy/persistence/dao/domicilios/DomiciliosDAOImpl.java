/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.domicilios;

import java.io.Serializable;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import turing.solutions.dy.persistence.dao.GenericDAOImpl;

/**
 *
 * @author Alan
 * @param <T>
 */
@Repository
public class DomiciliosDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements DomiciliosDAO<T> {

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public T findById(Integer o) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByIdDomicilio");
        query.setParameter("idDomicilio", o);
        Object obj = query.uniqueResult();
        return obj != null ? (T) obj : null;
    }
    
    
}
