package com.qkcare.service;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.tuple.Triple;
import org.javatuples.Quartet;
import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;

@Service(value="genericService")
public interface GenericService {
	
	public BaseEntity save(BaseEntity entity);
	public void delete(BaseEntity entity);
	public void delete(Class cl, List<Long> ids);
	public BaseEntity find(Class cl, Long key);
	public List<BaseEntity> getAll(Class cl);
	public List<BaseEntity> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, 
			String orderBy);
	public List<Object[]> getNativeByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, 
			String orderBy, String groupBy);
	public Integer deleteByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters);
}
