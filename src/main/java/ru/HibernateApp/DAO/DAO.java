package ru.HibernateApp.DAO;

import java.util.List;

public interface DAO<T, K> {
	void create(T entity);

	T read(K key);

	void delete(K key);

	void update(K key, T entity);

	List<T> selectAll();

}
