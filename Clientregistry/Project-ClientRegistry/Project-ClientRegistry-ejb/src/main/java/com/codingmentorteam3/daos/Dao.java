package com.codingmentorteam3.daos;

import java.io.Serializable;

/**
 *
 * @author norbeee sch.norbeee@gmail.com
 * @param <T>
 */
public interface Dao<T extends Serializable> {

    T create(T entity);

    T read(Long id);

    T update(T entity);

    T delete(T entity);

}
