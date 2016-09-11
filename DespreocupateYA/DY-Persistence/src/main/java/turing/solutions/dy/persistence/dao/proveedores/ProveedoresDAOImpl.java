/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.proveedores;

import java.io.Serializable;
import java.util.List;
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
public class ProveedoresDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements ProveedoresDAO<T> {

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public T findById(Integer o) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByIdProveedor");
        query.setParameter("idProveedor", o);
        Object result = query.uniqueResult();
        return result != null ? (T) result : null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public List<T> findAll(Class<T> o) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(o.getSimpleName() + ".findAllOrderById");
        return (List<T>) query.list();
    }

}
