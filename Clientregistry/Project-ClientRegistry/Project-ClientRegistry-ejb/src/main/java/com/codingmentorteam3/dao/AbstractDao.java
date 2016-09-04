package com.codingmentorteam3.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 * @param <T>
 */
public abstract class AbstractDao<T extends Serializable> implements Dao<T> {

    @PersistenceContext(unitName = "ClientRegistry_PU")
    protected EntityManager em;
    protected final Class<T> clazz;
    
    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T read(Long id) {
        return em.find(clazz, id);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public T delete(T entity) {
        em.remove(entity);
        return entity;
    }
    
}