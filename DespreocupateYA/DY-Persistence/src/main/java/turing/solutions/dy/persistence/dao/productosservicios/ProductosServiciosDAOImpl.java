/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.productosservicios;

import java.io.Serializable;
import turing.solutions.dy.persistence.dao.GenericDAOImpl;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alan
 */
@Repository
public class ProductosServiciosDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements ProductosServiciosDAO<T> {

    @Override
    @Transactional(propagation = Propagation.MANDATORY,readOnly = true)
    public T findById(Integer o) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByIdProdserv");
        query.setParameter("idProdserv", o);
        Object result = query.uniqueResult();
        return result != null ? (T) result : null;
    }
    
}
