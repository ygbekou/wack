package com.qkcare.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.dao.GenericDao;
import com.qkcare.model.BaseEntity;

@Service(value="genericService")
public class GenericServiceImpl implements GenericService {
	
	Map<String, List<String>> entityCascades;
	
	@Autowired
	GenericDao<BaseEntity, String> genericDao;
	
	public GenericServiceImpl() {
		this.entityCascades = new HashMap<String, List<String>>();
		this.entityCascades.put("Department", Arrays.asList(new String[]{"User"}));
	}
	
	@Transactional
	public BaseEntity save(BaseEntity entity) {	
		entity.setModDate(new Date());                    
		entity.setModifiedBy(1L);    
		if (entity.getId() == null) {
			entity.setCreateDate(new Date());                  
			return this.genericDao.persist(entity);
		} else {	
			return this.genericDao.merge(entity);
		}
				             
	}
	
	@Transactional
	public void delete(BaseEntity entity) {
		this.genericDao.delete(entity);
	}
	
	@Transactional
	public void deleteCascade(String parentEntity, String entityName, Long id) {
		String query = "DELETE FROM " + entityName + " WHERE " + parentEntity + "_ID IN (SELECT " + parentEntity ;
		List<String> childEntities = this.entityCascades.get(entityName);
		for (String childEntity : childEntities) {
			deleteCascade(entityName, childEntity, id);
		}
		//this.genericDao.delete(entity);
	}
	
	@Transactional
	public void delete(Class cl, List<Long> ids) {
		this.genericDao.delete(cl, ids);
	}

	public BaseEntity find(Class cl, Long key) {
		return (BaseEntity) this.genericDao.find(cl, key);
	}

	public List<BaseEntity> getAll(Class cl) {
		return this.genericDao.getAll(cl);
	}
	
	public List<BaseEntity> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters) {
		return this.genericDao.getByCriteria(queryStr, parameters);
	}

}
