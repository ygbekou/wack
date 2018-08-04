package com.qkcare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.tuple.Triple;
import org.apache.log4j.Logger;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qkcare.model.BaseEntity;

@SuppressWarnings("unchecked")
@Repository
public class GenericDaoImpl<E, K> implements GenericDao<E, K> {
	private static Logger logger = Logger.getLogger(GenericDaoImpl.class) ;
	
	@Autowired
	private EntityManager entityManager;

	public E persist(E entity) {
	    entityManager.persist(entity);
		return entity;
	}

	public E merge(E entity) {
	    entityManager.merge(entity);
		return entity;
	}
	
	public void delete(E entity) {
		entityManager.remove(entity);
	}

	public void delete(Class cl, List<Long> ids) {
		/*CriteriaDelete criteria = entityManager.getCriteriaBuilder().createCriteriaDelete(cl);
		Root root = criteria.from(cl);
		criteria.where(root.get("id").in(ids));

		int result = entityManager.createQuery(criteria).executeUpdate();
		logger.info("delete @" + result);*/
		
		for (Long id : ids) {
			this.delete(this.find(cl, id));
		}
	}
	
	public E find(Class cl, Long key) {
		return (E) entityManager.find(cl, key);
	}

	public List<E> getAll(Class cl) {
		CriteriaQuery<E> criteria = entityManager.getCriteriaBuilder().createQuery(cl);
	    criteria.select(criteria.from(cl));
	    List<E> ListOfEmailDomains = entityManager.createQuery(criteria).getResultList();
	    return ListOfEmailDomains;
	}

	public List<E> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, String orderBy) {
		
		StringBuilder queryBuilder = new StringBuilder(queryStr);
		
		// Build the query
		for (Quartet<String, String, String, String> parameter : parameters) {
			queryBuilder.append(" AND " + parameter.getValue0() + " :" + parameter.getValue1());
		}
		
		if (orderBy != null) {
			queryBuilder.append(orderBy);
		}
		
		Query query = entityManager.createQuery(queryBuilder.toString());
		// Set the parameters on the query
		for (Quartet<String, String, String, String> parameter : parameters) {
			if ("Long".equals(parameter.getValue3())) {
				query.setParameter(parameter.getValue1(), new Long(parameter.getValue2()));
			} else if ("Integer".equals(parameter.getValue3())) {
				query.setParameter(parameter.getValue1(), new Integer(parameter.getValue2()));
			} else if ("String".equals(parameter.getValue3())) {
				query.setParameter(parameter.getValue1(), new String(parameter.getValue2()));
			}
		}
		
	    List<E> ListOfEmailDomains = query.getResultList();
	    return ListOfEmailDomains;
	}

}
