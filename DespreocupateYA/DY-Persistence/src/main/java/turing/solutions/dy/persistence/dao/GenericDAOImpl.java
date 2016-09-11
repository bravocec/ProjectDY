/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turing.solutions.dy.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alan
 * @param <T>
 */
public abstract class GenericDAOImpl<T extends Serializable> implements GenericDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Integer save(T o) {
        Session session = this.sessionFactory.getCurrentSession();
        Integer id = (Integer) session.save(o);
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void update(T o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(o);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(T o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(o);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.MANDATORY)
    public List<T> findAll(Class<T> o) {
        Query query = this.sessionFactory.getCurrentSession().getNamedQuery(o.getSimpleName() + ".findAll");
        return (List<T>) query.list();
    }

    /*
    @Override
    public T getEntity() {
        Type instance = getClass().getGenericSuperclass();
        Type argument = ((ParameterizedType) instance).getActualTypeArguments()[0];
        T clazz;
        try {
            clazz = (T) (Class.forName(argument.toString())).newInstance();
        } catch (Throwable e) {
            System.out.println("No se pudo obtener la instancia");
            e.printStackTrace();
            throw new RuntimeException("No se pudo obtener la instancia", e);
        }
        return clazz;
    }
    */
}
