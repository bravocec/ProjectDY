/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.telefonos;

import java.io.Serializable;
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
public class TipoTelefonoDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements TipoTelefonoDAO<T> {

    @Override
    public T findById(Integer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public T findByDescripcion(String descripcion) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME + ".findByDescripcion");
        query.setParameter("descripcion", descripcion);
        Object result = query.uniqueResult();
        return result != null ? (T) result : null;
    }

}
