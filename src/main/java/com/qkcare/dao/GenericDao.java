package com.qkcare.dao;

import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.javatuples.Quartet;

public interface GenericDao<E,K> {
	public E persist(E entity);
	public E merge(E entity);
	public void delete(E entity);
	public void delete(Class cl, List<Long> ids);
	public E find(Class cl, Long key);
	public List<E> getAll(Class cl);
	public List<E> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters);
}
