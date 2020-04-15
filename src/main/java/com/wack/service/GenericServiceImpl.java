package com.wack.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.dao.GenericDao;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.util.Constants;

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
	public BaseEntity saveWithFiles(BaseEntity entity, List<MultipartFile> files, 
			boolean useId, List<String> attributeNames) {
		this.save(entity);
		
		try {
			int i = 0;
			for (MultipartFile file : files) {
				String originalFileExtension = file.getOriginalFilename()
						.substring(file.getOriginalFilename().lastIndexOf("."));
				
				String fileName = saveFile(file, entity.getId(), entity.getClass().getSimpleName(), 
						useId ? entity.getId() + originalFileExtension : file.getOriginalFilename());
				
				String fieldName = useId ? attributeNames.get(i) : file.getOriginalFilename().split("\\.")[0];
				Field field = null;
				try {
					field = entity.getClass().getDeclaredField(fieldName);
				} catch(Exception ex) {
					continue;
				}
				
				field.setAccessible(true);
		        field.set(entity, fileName);
		        this.save(entity);
		        i++;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
				
		return entity;
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
	
	@Transactional
	public void deleteFile(Class cl, List<Long> ids, String fileName) {
		for (Long id: ids) {
			this.deleteFile(cl.getSimpleName(), id, fileName);
		}
	}

	public BaseEntity find(Class cl, Long key) {
		return (BaseEntity) this.genericDao.find(cl, key);
	}
	
	public BaseEntity findWithFiles(Class cl, Long key) {
		BaseEntity entity = (BaseEntity) this.genericDao.find(cl, key);
		if (entity.getId() != null) {
			entity.setFileNames(this.getFiles(entity.getId(), entity.getClass().getSimpleName()));
		}
		
		return entity;
	}

	public List<BaseEntity> getAll(Class cl) {
		return this.genericDao.getAll(cl);
	}
	
	public List<BaseEntity> getByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, 
			String orderBy) {
		return this.genericDao.getByCriteria(queryStr, parameters, orderBy);
	}

	public List<Object[]> getNativeByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters, 
			String orderBy, String groupBy) {
		return this.genericDao.getNativeByCriteria(queryStr, parameters, orderBy, groupBy);
	}
	
	public Integer deleteByCriteria(String queryStr, List<Quartet<String, String, String, String>> parameters) {
		return this.genericDao.deleteByCriteria(queryStr, parameters);
	}
	
	public Session getConnection() {
		return this.genericDao.getConnection();
	}
	
	private String saveFile(MultipartFile file, Long entityId, String entityName, String fileName) {
		if (!file.isEmpty()) {
			try {
	
				// transfer to upload folder
				String storageDirectory = null;
				if (entityName != null) {					
					storageDirectory = Constants.DOC_FOLDER	+ entityName + File.separator + entityId + File.separator;
					File dir = new File(storageDirectory);
					if (!dir.exists()) {
						dir.mkdirs();
					}
	
				} else {
					throw new Exception("Unknown");
				}
				
				
				File newFile = new File(storageDirectory + "/" + fileName);
		        file.transferTo(newFile);
		        
		        return fileName;
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private List<String> getFiles(Long entityId, String entityName) {
		List<String> fileNames = new ArrayList<>();
		try {
	
				// transfer to upload folder
			String storageDirectory = null;
			if (entityName != null) {					
				storageDirectory = Constants.DOC_FOLDER	+ entityName + File.separator + entityId + File.separator;
				File dir = new File(storageDirectory);
				if (dir.exists()) {
					File[] files = dir.listFiles();
					for (File file: files) {
						fileNames.add(file.getName());
					}
				}

			} 
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileNames;
	    
	}
	
	
	private void deleteFile(String entityName, Long entityId, String fileName) {
	
		try {

			String storageDirectory = null;
			if (entityName != null) {					
				storageDirectory = Constants.DOC_FOLDER	+ entityName + File.separator + entityId + File.separator;
				File dir = new File(storageDirectory);
				if (dir.exists()) {
					File file = new File(storageDirectory + "/" + fileName);
					if (file.exists()) {
						file.delete();
					}
				}

			} else {
				throw new Exception("Unknown");
			}
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Company getCompany(String language) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		
		paramTupleList.add(Quartet.with("c.language = ", "language", language, "String"));
		paramTupleList.add(Quartet.with("c.status = ", "status", "0", "Integer"));
		String queryStr =  "SELECT c FROM Company c WHERE 1 = 1"; 
		List<BaseEntity> companies = this.getByCriteria(queryStr, paramTupleList, null);
		if (companies.size() > 0) {
			return (Company) companies.get(0);
		}
		
		return null;
		
	}
}
