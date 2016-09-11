/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.categorias;

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
public class CategoriasServiciosDAOImpl<T extends Serializable> extends GenericDAOImpl<T> implements CategoriasServiciosDAO<T> {
    

    @Override
    public T findById(Integer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public T findCategoriaByDescripcion(String desc) {
        final String str = ENTITY_NAME + ".findByDescripcion";
        
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(str);
        query.setParameter("descripcion", desc);
        
        Object result = query.uniqueResult();
        
        return result != null ? (T) result : null;
    }

}
