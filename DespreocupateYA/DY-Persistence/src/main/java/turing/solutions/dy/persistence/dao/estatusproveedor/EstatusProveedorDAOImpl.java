/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao.estatusproveedor;

import java.io.Serializable;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import turing.solutions.dy.persistence.dao.GenericDAOImpl;

/**
 *
 * @author Alan
 */
@Repository
public class EstatusProveedorDAOImpl<T extends Serializable> extends GenericDAOImpl<T>  implements EstatusProveedorDAO<T> {

    @Override
    public T findById(Integer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T findEstatusByDescripcion(String desc) {
        Query query = getSessionFactory().getCurrentSession().getNamedQuery(ENTITY_NAME+".findByDescripcion");
        query.setParameter("descripcion", desc);
        
        Object result = query.uniqueResult();
        return result != null ? (T) result : null;
    }
    
}
