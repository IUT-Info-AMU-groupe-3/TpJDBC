package fr.amu.jdbc.dao;

import java.util.List;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public interface DAO<T> {
    public T insert(T obj);
    public boolean delete(T obj);
    public boolean update(T obj);
    public T getById(int id);
    public List<T> findAll();
}
